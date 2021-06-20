package com.ottoboni.takeawaychallenge.coredata.domain.mapper

interface Mapper<E, D> {
    fun toDomain(entity: E): D

    fun fromDomain(domain: D): E
}
