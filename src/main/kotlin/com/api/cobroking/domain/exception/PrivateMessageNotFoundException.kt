package com.api.cobroking.domain.exception

class PrivateMessageNotFoundException(
    message: String? = "Private message not found"
) : RuntimeException(message)