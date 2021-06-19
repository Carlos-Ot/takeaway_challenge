package com.ottoboni.takeawaychallenge.coredata.factory

import com.ottoboni.takeawaychallenge.coredata.domain.factory.SortingValuesFactory
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.SortingValuesData
import org.junit.Test

class SortingValuesFactoryTest {

    private val emptySortingValuesData = SortingValuesData(
        bestMatch = null,
        newest = null,
        ratingAverage = null,
        distance = null,
        popularity = null,
        averageProductPrice = null,
        deliveryCosts = null,
        minCost = null
    )

    private val nonEmptySortingValuesData = SortingValuesData(
        bestMatch = 1.0f,
        newest = 10.0f,
        ratingAverage = 7.0f,
        distance = 1000,
        popularity = 4.5f,
        averageProductPrice = 2000,
        deliveryCosts = 1000,
        minCost = 1500
    )

    private val factory = SortingValuesFactory()

    @Test
    fun `test empty data should not be null`() {
        val sortingValues = factory.make(emptySortingValuesData)

        assertThat(sortingValues.bestMatch).isNotNull()
        assertThat(sortingValues.newest).isNotNull()
        assertThat(sortingValues.ratingAverage).isNotNull()
        assertThat(sortingValues.distance).isNotNull()
        assertThat(sortingValues.popularity).isNotNull()
        assertThat(sortingValues.averageProductPrice).isNotNull()
        assertThat(sortingValues.deliveryCosts).isNotNull()
        assertThat(sortingValues.minCost).isNotNull()
    }

    @Test
    fun `test sortingValues should match sortingValuesData`() {
        val sortingValues = factory.make(nonEmptySortingValuesData)

        assertThat(sortingValues.bestMatch).isEqualTo(nonEmptySortingValuesData.bestMatch)
        assertThat(sortingValues.newest).isEqualTo(nonEmptySortingValuesData.newest)
        assertThat(sortingValues.ratingAverage).isEqualTo(nonEmptySortingValuesData.ratingAverage)
        assertThat(sortingValues.distance).isEqualTo(nonEmptySortingValuesData.distance)
        assertThat(sortingValues.popularity).isEqualTo(nonEmptySortingValuesData.popularity)
        assertThat(sortingValues.averageProductPrice).isEqualTo(nonEmptySortingValuesData.averageProductPrice)
        assertThat(sortingValues.deliveryCosts).isEqualTo(nonEmptySortingValuesData.deliveryCosts)
        assertThat(sortingValues.minCost).isEqualTo(nonEmptySortingValuesData.minCost)
    }
}