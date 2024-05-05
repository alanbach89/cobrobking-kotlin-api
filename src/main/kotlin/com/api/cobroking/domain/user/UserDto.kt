package com.api.cobroking.domain.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDto(
    val id: Long? = null,
    @field:NotBlank
    @field:Size(min = 4, max = 20, message = "Username must be have from 4 to 20 characters")
    val username: String,
    @field:NotBlank
    @field:Email
    val email: String,
    @field:NotBlank
    val type: UserTypeEnum,
    @field:NotBlank
    @field:Size(min = 1, max = 50, message = "Firstname must be have from 1 to 50 characters")
    val firstname: String,
    @field:NotBlank
    @field:Size(min = 1, max = 50, message = "Lastname must be have from 1 to 50 characters")
    val lastname: String,
    @field:NotBlank
    val nationality: String,
    @field:NotBlank
    val documentType: DocumentTypeEnum,
    @field:NotBlank
    @field:Size(min = 8, max = 20, message = "Document must be have from 4 to 20 characters")
    val document: String,
    @field:NotBlank
    val phone: String
)

fun User.toUserDto() = UserDto(
    id = id,
    username = username,
    email = email,
    type = type,
    firstname = firstname,
    lastname = lastname,
    document = document,
    documentType = documentType,
    nationality = nationality,
    phone = phone
)