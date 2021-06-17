package com.example.coredata.domain.model

import com.example.coredata.domain.model.enums.OpeningStatus

data class Restaurant(
    val name: String,
    val status: OpeningStatus?,
    val isFavorite: Boolean,
    val sortingValues: SortingValues?,
)