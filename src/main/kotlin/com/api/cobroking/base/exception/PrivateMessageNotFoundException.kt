package com.api.cobroking.base.exception

class PrivateMessageNotFoundException(
    message: String? = "Private message not found"
) : RuntimeException(message)