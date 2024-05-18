package com.api.cobroking.base.exception

class PrivateMessageExistsException(
    message: String? = "Private message with the same id already exists"
) : RuntimeException(message)