package com.api.cobroking.domain.user

import com.api.cobroking.annotation.NoArg
import com.api.cobroking.domain.conversation.PrivateConversation
import com.api.cobroking.domain.conversation.PrivateMessage
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import jakarta.validation.constraints.Email
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity
@NoArg
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Email
    @Column(nullable = false)
    var email: String,
    @Column(nullable = false)
    var type: UserTypeEnum,
    @Column(nullable = false)
    var firstname: String,
    @Column(nullable = false)
    var lastname: String,
    @Column(nullable = false)
    var nationality: String,
    @Column(nullable = false)
    var document: String,
    @Column(nullable = false)
    var documentType: DocumentTypeEnum,
    var phone: String,

    @OneToMany(mappedBy = "user")
    val sentMessages: List<PrivateMessage> = listOf(),

    @ManyToMany(mappedBy = "participants")
    val conversations: List<PrivateConversation> = listOf(),

    // inherits from UserDetails
    private var username: String,
    private val password: String,
    private val isEnabled: Boolean, //Disabled account can not log in
    private val isCredentialsNonExpired: Boolean, //credential can be expired,eg. Change the password every three months
    private val isAccountNonExpired: Boolean, //eg. Demo account（guest） can only be online  24 hours
    private val isAccountNonLocked: Boolean, //eg. Users who malicious attack system,lock their account for one year
    private val authorities: Set<GrantedAuthority>

) : UserDetails {

    constructor() : this(null, "", UserTypeEnum.NON_PAID_CUSTOMER, "", "", "",
        "", DocumentTypeEnum.DNI, "", listOf(), listOf(),"", "", false,
        false, false, false, setOf())

    override fun getUsername(): String = username
    override fun getPassword(): String = password
    override fun isEnabled(): Boolean = isEnabled
    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired
    override fun isAccountNonExpired(): Boolean = isAccountNonExpired
    override fun isAccountNonLocked(): Boolean = isAccountNonLocked
    override fun getAuthorities(): Set<out GrantedAuthority> = authorities

    fun updateFromDto(userDto: UserDto): User {
        this.email = email
        this.type
        this.firstname = firstname
        this.lastname = lastname
        this.documentType = documentType
        this.nationality = nationality
        this.phone = phone
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
        phone = phone
    )
}