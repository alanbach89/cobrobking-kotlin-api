package com.api.cobroking.domain.user

import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun existsUserByUsername(username: String): Boolean

    fun getByUsername(username: String): User?

    @EntityGraph(attributePaths = ["authorities"])
    fun findByUsername(username: String): Optional<User>

    fun findByEmail(email: String): Optional<User>

    fun findByUsernameIn(usernames: List<String>): MutableList<User>

    @Modifying
    @Query("UPDATE User u SET u.provider = :provider, u.lastLogin = CURRENT_TIMESTAMP WHERE u.username = :username")
    fun updateLastLogin(@Param("username") username: String, @Param("provider") provider: String)

}