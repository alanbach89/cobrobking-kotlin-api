package com.api.cobroking.domain.exception

class PrivateConversationExistsException(
    message: String? = "Private conversation with the same id already exists"
) : RuntimeException(message)