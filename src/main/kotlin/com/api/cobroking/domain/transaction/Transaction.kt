package com.api.cobroking.domain.transaction

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotNull


@Entity
class Transaction (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val publicationId: Long,

    @Column(nullable = false)
    val price: Double,

    @Enumerated
    @Column(nullable = false)
    val paymentTypeEnum: PaymentTypeEnum,

    @Enumerated
    @Column(nullable = false)
    val status: PaymentStatusEnum,

    var errorReason: String?
)