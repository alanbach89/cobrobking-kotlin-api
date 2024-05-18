package com.api.cobroking.base.exception

class PrivateConversationNotFoundException(
    message: String? = "Private conversation not found"
) : RuntimeException(message)