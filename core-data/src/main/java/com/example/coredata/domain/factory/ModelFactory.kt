package com.example.coredata.domain.factory

interface ModelFactory<in I, out O> {
    fun make(input: I): O
}