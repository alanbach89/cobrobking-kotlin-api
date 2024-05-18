package com.api.cobroking.domain.transaction

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
class Transaction (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val publicationId: Long,

    @Enumerated
    @Column(nullable = false)
    val publicationType: PublicationType,

    @Column(nullable = false)
    val price: Double,

    @Enumerated
    @Column(nullable = false)
    val paymentType: PaymentType,

    @Enumerated
    @Column(nullable = false)
    val status: PaymentStatus,

    var errorReason: String?
)