package com.api.cobroking.domain.transaction

import jakarta.persistence.*


@Entity
class Transaction (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val publicationId: Long,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val publicationType: PublicationType,

    @Column(nullable = false)
    val price: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val paymentType: PaymentType,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val status: PaymentStatus,

    var errorReason: String?
)