package com.api.cobroking.domain.security.jwt

import com.api.cobroking.domain.security.dtos.UserJwtDto
import io.jsonwebtoken.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@Service
class JwtService(
    @Value("\${token.signing.key}") private val secretKey: String? = null
) {

    private final val accessTokenValidity = (60 * 60 * 1000).toLong()
    private final val TOKEN_HEADER = "Authorization"
    private final val TOKEN_PREFIX = "Bearer "

    fun createToken(user: UserJwtDto): String {
        val claims: Claims = Jwts.claims()
        claims["firstname"] = user.firstname
        claims["lastname"] = user.lastname
        claims["email"] = user.email
        val tokenCreateTime = Date()
        val tokenValidity = Date(tokenCreateTime.time + accessTokenValidity.toDuration(DurationUnit.MILLISECONDS).inWholeMinutes)
        return Jwts.builder()
            .setSubject(user.username)
            .setClaims(claims)
            .setExpiration(tokenValidity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun resolveClaims(req: HttpServletRequest): Claims? {
        return try {
            val token = resolveToken(req)
            token?.let { parseJwtClaims(it) }
        } catch (ex: ExpiredJwtException) {
            req.setAttribute("expired", ex.message)
            throw ex
        } catch (ex: Exception) {
            req.setAttribute("invalid", ex.message)
            throw ex
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(TOKEN_HEADER)
        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            bearerToken.substring(TOKEN_PREFIX.length)
        } else null
    }

    @Throws(AuthenticationException::class)
    fun validateClaims(claimSet: Claims): Boolean {
        return claimSet.expiration.after(Date()) &&
                    SecurityContextHolder.getContext().authentication.principal == claimSet.subject
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val userName = extractUserName(token)
        return userName == userDetails.username && !validateClaims(parseJwtClaims(token)!!)
    }

    fun extractEmail(token: String): String {
        return parseJwtClaims(token)!!.subject
    }

    fun extractUserName(token: String): String {
        return parseJwtClaims(token)!!.subject
    }

    private fun extractRoles(claimSet: Claims): List<String> {
        return claimSet["roles"] as List<String>
    }

    private fun parseJwtClaims(token: String): Claims? {
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(token)
            .body;
    }
}