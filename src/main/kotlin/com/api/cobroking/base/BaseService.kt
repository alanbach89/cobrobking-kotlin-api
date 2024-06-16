package com.api.cobroking.base

interface BaseService<T,K> {

    fun create(dto: T): T

    fun update(id: K, dto: T): T

    fun getById(id: K): T

    fun deleteById(id: K)

    fun  getAll(): List<T>
}