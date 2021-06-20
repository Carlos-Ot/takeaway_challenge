package com.ottoboni.takeawaychallenge.coredata.mapper

import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.coredata.domain.mapper.SortingValuesMapper
import com.ottoboni.takeawaychallenge.coredata.domain.model.SortingValues
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.SortingValuesEntity
import org.junit.Test

class SortingValuesMapperTest {

    private val mapper = SortingValuesMapper()

    @Test
    fun `test entity data should match domain data`() {
        val sortingValues = SortingValues(
            bestMatch = 1.0f,
            newest = 10.0f,
            ratingAverage = 7.0f,
            distance = 1000,
            popularity = 4.5f,
            averageProductPrice = 2000,
            deliveryCosts = 1000,
            minCost = 1500
        )

        val sortingValuesEntity = mapper.fromDomain(sortingValues)

        assertThat(sortingValuesEntity.bestMatch).isEqualTo(sortingValues.bestMatch)
        assertThat(sortingValuesEntity.newest).isEqualTo(sortingValues.newest)
        assertThat(sortingValuesEntity.ratingAverage).isEqualTo(sortingValues.ratingAverage)
        assertThat(sortingValuesEntity.distance).isEqualTo(sortingValues.distance)
        assertThat(sortingValuesEntity.popularity).isEqualTo(sortingValues.popularity)
        assertThat(sortingValuesEntity.averageProductPrice).isEqualTo(
            sortingValues.averageProductPrice
        )
        assertThat(sortingValuesEntity.deliveryCosts).isEqualTo(sortingValues.deliveryCosts)
        assertThat(sortingValuesEntity.minCost).isEqualTo(sortingValues.minCost)
    }

    @Test
    fun `test domain data should match entity data`() {
        val sortingValuesEntity = SortingValuesEntity(
            bestMatch = 1.0f,
            newest = 10.0f,
            ratingAverage = 7.0f,
            distance = 1000,
            popularity = 4.5f,
            averageProductPrice = 2000,
            deliveryCosts = 1000,
            minCost = 1500
        )

        val sortingValues = mapper.toDomain(sortingValuesEntity)

        assertThat(sortingValues.bestMatch).isEqualTo(sortingValuesEntity.bestMatch)
        assertThat(sortingValues.newest).isEqualTo(sortingValuesEntity.newest)
        assertThat(sortingValues.ratingAverage).isEqualTo(sortingValuesEntity.ratingAverage)
        assertThat(sortingValues.distance).isEqualTo(sortingValuesEntity.distance)
        assertThat(sortingValues.popularity).isEqualTo(sortingValuesEntity.popularity)
        assertThat(sortingValues.averageProductPrice).isEqualTo(
            sortingValuesEntity.averageProductPrice
        )
        assertThat(sortingValues.deliveryCosts).isEqualTo(sortingValuesEntity.deliveryCosts)
        assertThat(sortingValues.minCost).isEqualTo(sortingValuesEntity.minCost)
    }
}
