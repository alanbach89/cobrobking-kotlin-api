package com.api.cobroking.domain.user

import com.api.cobroking.domain.conversation.PrivateConversation
import com.api.cobroking.domain.conversation.PrivateMessage
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Email
import org.hibernate.annotations.JdbcTypeCode
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.sql.Timestamp
import java.util.UUID


@Entity
data class User(
    @Id @JdbcTypeCode(java.sql.Types.VARCHAR)
    var id: UUID?,
    private var username: String,
    private var password: String,
    @Email
    @Column(nullable = false)
    var email: String,
    @Column(nullable = false)
    var type: UserType,
    @Column(nullable = false)
    var firstname: String,
    @Column(nullable = false)
    var lastname: String,
    @Column(nullable = false)
    var nationality: String,
    @Column(nullable = false)
    var document: String,
    @Column(nullable = false)
    var documentType: DocumentType,
    var phone: String,

    @OneToMany(mappedBy = "user")
    val sentMessages: List<PrivateMessage> = listOf(),

    @ManyToMany(mappedBy = "participants")
    val conversations: List<PrivateConversation> = listOf(),

    // inherits from UserDetails
    private var isEnabled: Boolean, //Disabled account can not log in
    private var isCredentialsNonExpired: Boolean, //credential can be expired,eg. Change the password every three months
    private var isAccountNonExpired: Boolean, //eg. Demo account（guest） can only be online  24 hours
    private var isAccountNonLocked: Boolean, //eg. Users who malicious attack system,lock their account for one year
    private var authorities: Set<GrantedAuthority>,
    var provider: String,
    var providerId: String,
    var imgUrl: String,
    var lastLogin: Timestamp?

) : UserDetails {

    constructor() : this(null, "", "", "", UserType.NON_PAID_CUSTOMER, "", "",
        "", "", DocumentType.DNI, "", listOf(), listOf(),false,
        false, false, false, setOf(), "", "",
        "", null
    )

    override fun getUsername(): String = username

    fun setUsername(username: String) { this.username = username }
    override fun getPassword(): String = password
    fun setPassword(password: String) { this.password = password }
    override fun isEnabled(): Boolean = isEnabled
    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired
    override fun isAccountNonExpired(): Boolean = isAccountNonExpired
    override fun isAccountNonLocked(): Boolean = isAccountNonLocked
    override fun getAuthorities(): Set<GrantedAuthority> = authorities

    fun updateFromDto(userDto: UserDto): User {
        this.email = email
        this.type
        this.firstname = firstname
        this.lastname = lastname
        this.documentType = documentType
        this.nationality = nationality
        this.phone = phone
        this.imgUrl = imgUrl
        return this
    }

    fun createFromDto(userDto: UserDto): User {
        this.username = username
        this.email = email
        this.type
        this.firstname = firstname
        this.lastname = lastname
        this.documentType = documentType
        this.nationality = nationality
        this.phone = phone
        return this
    }

    fun toUserDto() = UserDto(
        id = id,
        username = username,
        email = email,
        type = type,
        firstname = firstname,
        lastname = lastname,
        document = document,
        documentType = documentType,
        nationality = nationality,
        phone = phone,
        imgUrl = imgUrl
    )
}