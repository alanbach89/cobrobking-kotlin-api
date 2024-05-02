package com.api.cobroking.domain.exception

class PrivateConversationNotFoundException(
    message: String? = "Private conversation not found"
) : RuntimeException(message)