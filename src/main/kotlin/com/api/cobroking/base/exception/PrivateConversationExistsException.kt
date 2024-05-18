package com.api.cobroking.base.exception

class PrivateConversationExistsException(
    message: String? = "Private conversation with the same id already exists"
) : RuntimeException(message)