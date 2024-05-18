package com.api.cobroking.base.exception

class UserRequestPublicationNotFoundException(
    message: String? = "User Request Publication not found"
) : RuntimeException(message)