package com.api.cobroking.domain.publication.request;

import com.api.cobroking.domain.publication.property.PublicationStatus
import com.api.cobroking.domain.user.User
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull


@Entity
@Table(name = "publication_user_request")
class UserRequestPublication (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var text: String,

    @ManyToOne
    var user: User,

    @Column(nullable = false)
    @Enumerated
    var status: PublicationStatus = PublicationStatus.INACTIVE
) {
    fun updateFromDto(userRequestPublicationDto: UserRequestPublicationDto): UserRequestPublication {
        this.title = title
        this.text = text
        this.status = status
        return this
    }

    fun toUserRequestPublicationDto() = UserRequestPublicationDto(
        id = id!!,
        title = title,
        text = text,
        userId = user.id!!,
        status = status
    )
}