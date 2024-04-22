package com.api.cobroking.domain.exception

class UserExistsException(
    message: String? = "User with the same username already exists"
) : RuntimeException(message)