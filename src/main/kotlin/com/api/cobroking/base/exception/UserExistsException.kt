package com.api.cobroking.base.exception

class UserExistsException(
    message: String? = "User with the same username already exists"
) : RuntimeException(message)