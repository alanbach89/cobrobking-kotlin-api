package com.api.cobroking.domain.user

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.hibernate.annotations.JdbcTypeCode
import org.springframework.security.core.GrantedAuthority
import java.sql.Types
import java.util.*


@Entity
data class Authority (
    @Id
    @JdbcTypeCode(Types.VARCHAR)
    private val id: UUID? = null,
    private var authority: String? = null
) : GrantedAuthority {
    override fun getAuthority(): String {
        return this.authority!!
    }
}