package com.api.cobroking.base.exception

class PropertyNotFoundException(
    message: String? = "Property not found"
) : RuntimeException(message)