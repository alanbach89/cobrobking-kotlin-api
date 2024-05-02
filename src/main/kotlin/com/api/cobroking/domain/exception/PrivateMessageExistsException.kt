package com.api.cobroking.domain.exception

class PrivateMessageExistsException(
    message: String? = "Private message with the same id already exists"
) : RuntimeException(message)