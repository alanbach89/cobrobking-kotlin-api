package com.api.cobroking.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun existsUserByUsername(username: String): Boolean

    fun getByUsername(username: String): User?

    fun findByUsername(username: String): Optional<User>

    fun findByUsernameIn(usernames: List<String>): MutableList<User>

}