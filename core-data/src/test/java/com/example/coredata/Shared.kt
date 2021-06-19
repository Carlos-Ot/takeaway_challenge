package com.example.coredata

import com.example.coredata.domain.model.Restaurant
import com.example.coredata.domain.model.SortingValues
import com.example.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity
import com.ottoboni.corelocalstorage.database.entity.SortingValuesEntity
import com.ottoboni.corelocalstorage.filestore.data.RestaurantData
import com.ottoboni.corelocalstorage.filestore.data.RestaurantListData
import com.ottoboni.corelocalstorage.filestore.data.SortingValuesData
import com.ottoboni.corelocalstorage.filestore.data.enums.OpeningStatusData
import io.mockk.every
import io.mockk.mockk

object Shared {

    fun mockRestaurantListData(amount: Int) = mockk<RestaurantListData> {
        every { restaurants } returns mockRestaurantDataList(amount)
    }

    private fun mockRestaurantDataList(amount: Int) = mutableListOf<RestaurantData>()
        .apply { repeat(amount) { this += mockRestaurantData(it) } }
        .toList()

    private fun mockRestaurantData(id: Int) = mockk<RestaurantData> {
        every { name } returns "name$id"
        every { status } returns OpeningStatusData.OPEN
        every { sortingValues } returns mockSortingValuesData()
    }

    private fun mockSortingValuesData() = mockk<SortingValuesData>() {
        every { bestMatch } returns 11.0f
        every { newest } returns 4.2f
        every { ratingAverage } returns 9.9f
        every { distance } returns 1000
        every { popularity } returns 9.0f
        every { averageProductPrice } returns 1223
        every { deliveryCosts } returns 100
        every { minCost } returns 900
    }

    fun mockRestaurant(id: Int = 0) = mockk<Restaurant>() {
        every { name } returns "name$id"
        every { status } returns OpeningStatus.OPEN
        every { sortingValues } returns mockSortingValues()
    }

    private fun mockSortingValues() = mockk<SortingValues>() {
        every { bestMatch } returns 11.0f
        every { newest } returns 4.2f
        every { ratingAverage } returns 9.9f
        every { distance } returns 1000
        every { popularity } returns 9.0f
        every { averageProductPrice } returns 1223
        every { deliveryCosts } returns 100
        every { minCost } returns 900
    }

    fun mockRestaurantEntityList(amount: Int) = mutableListOf<RestaurantEntity>()
        .apply { repeat(amount) { this += mockRestaurantEntity(it.toLong()) } }
        .toList()

    fun mockRestaurantEntity(mockId: Long = 0) = mockk<RestaurantEntity> {
        every { id } returns mockId
        every { name } returns "name$mockId"
        every { status } returns "open"
        every { sortingValues } returns mockSortingValuesEntity()
    }

    private fun mockSortingValuesEntity() = mockk<SortingValuesEntity> {
        every { bestMatch } returns 11.0f
        every { newest } returns 4.2f
        every { ratingAverage } returns 9.9f
        every { distance } returns 1000
        every { popularity } returns 9.0f
        every { averageProductPrice } returns 1223
        every { deliveryCosts } returns 100
        every { minCost } returns 900
    }
}