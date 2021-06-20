package com.ottoboni.takeawaychallenge.coredata.domain.model

import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Restaurant(
    val name: String?,
    val status: OpeningStatus,
    val isFavorite: Boolean,
    val sortingValues: SortingValues?,
) : Parcelable