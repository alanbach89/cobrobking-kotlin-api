package com.api.cobroking.base

interface BaseService<T> {

    fun create(dto: T): T

    fun update(id: Long, dto: T): T

    fun getById(id: Long): T

    fun deleteById(id: Long)

    fun  getAll(): List<T>
}