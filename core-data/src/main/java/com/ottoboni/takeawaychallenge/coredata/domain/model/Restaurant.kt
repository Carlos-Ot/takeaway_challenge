package com.ottoboni.takeawaychallenge.coredata.domain.model

import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus

data class Restaurant(
    val name: String?,
    val status: OpeningStatus,
    val isFavorite: Boolean,
    val sortingValues: SortingValues?,
)