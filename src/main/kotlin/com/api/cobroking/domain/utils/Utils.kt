package com.api.cobroking.domain.utils

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails


class Utils {

    companion object {
        fun getCurrentUsername(): String {
            val principal = SecurityContextHolder.getContext().authentication.principal

            if (principal is UserDetails) {
                return principal.username
            } else {
                return principal.toString()
            }
        }
    }
}