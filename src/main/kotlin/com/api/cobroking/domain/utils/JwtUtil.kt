package com.api.cobroking.domain.utils

import com.api.cobroking.domain.user.User
import com.nimbusds.jwt.JWTParser
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component
import java.util.Date
import kotlin.time.DurationUnit
import kotlin.time.toDuration


@Component
class JwtUtil {
    private val secret_key = "mysecretkey"
    private val accessTokenValidity = (60 * 60 * 1000).toLong()
    private val jwtParser: JWTParser
    private val TOKEN_HEADER = "Authorization"
    private val TOKEN_PREFIX = "Bearer "

    init {
        jwtParser = Jwts.parser().setSigningKey(secret_key) as JWTParser
    }

    fun createToken(user: User): String {
        val claims: Claims = Jwts.claims().setSubject(user.email)
        claims.put("firstname", user.firstname)
        claims.put("lastname", user.lastname)
        val tokenCreateTime = Date()
        val tokenValidity = Date(tokenCreateTime.getTime() + accessTokenValidity.toDuration(DurationUnit.MILLISECONDS).inWholeMinutes)
        return Jwts.builder()
            .setClaims(claims)
            .setExpiration(tokenValidity)
            .signWith(SignatureAlgorithm.HS256, secret_key)
            .compact()
    }

    private fun parseJwtClaims(token: String): Claims {
        return JWTParser.parse(token).jwtClaimsSet
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
    fun validateClaims(claims: Claims): Boolean {
        return try {
            claims.getExpiration().after(Date())
        } catch (e: Exception) {
            throw e
        }
    }

    fun getEmail(claims: Claims): String {
        return claims.getSubject()
    }

    private fun getRoles(claims: Claims): List<String> {
        return claims.get("roles") as List<String>
    }
}