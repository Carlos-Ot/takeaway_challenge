package com.example.coredata.mapper

import com.example.coredata.domain.mapper.RestaurantMapper
import com.example.coredata.domain.mapper.SortingValuesMapper
import com.example.coredata.domain.model.Restaurant
import com.example.coredata.domain.model.SortingValues
import com.example.coredata.domain.model.enums.OpeningStatus
import com.google.common.truth.Truth.assertThat
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity
import com.ottoboni.corelocalstorage.database.entity.SortingValuesEntity
import org.junit.Test

class RestaurantMapperTest {

    private val mapper = RestaurantMapper(SortingValuesMapper())

    private val sortingValues = SortingValues(
        bestMatch = 1.0f,
        newest = 10.0f,
        ratingAverage = 7.0f,
        distance = 1000,
        popularity = 4.5f,
        averageProductPrice = 2000,
        deliveryCosts = 1000,
        minCost = 1500
    )

    private val sortingValuesEntity = SortingValuesEntity(
        bestMatch = 1.0f,
        newest = 10.0f,
        ratingAverage = 7.0f,
        distance = 1000,
        popularity = 4.5f,
        averageProductPrice = 2000,
        deliveryCosts = 1000,
        minCost = 1500
    )

    @Test
    fun `test entity data should match domain data`() {
        val restaurant = Restaurant(
            name = "Domain Restaurant",
            status = OpeningStatus.OPEN,
            isFavorite = true,
            sortingValues = sortingValues
        )

        val restaurantEntity = mapper.fromDomain(restaurant)

        assertThat(restaurantEntity.name).isEqualTo(restaurant.name)
        assertThat(restaurantEntity.status).isEqualTo(restaurant.status.toString())
        assertThat(restaurantEntity.sortingValues?.bestMatch).isEqualTo(sortingValues.bestMatch)
        assertThat(restaurantEntity.sortingValues?.newest).isEqualTo(sortingValues.newest)
        assertThat(restaurantEntity.sortingValues?.ratingAverage).isEqualTo(sortingValues.ratingAverage)
        assertThat(restaurantEntity.sortingValues?.distance).isEqualTo(sortingValues.distance)
        assertThat(restaurantEntity.sortingValues?.popularity).isEqualTo(sortingValues.popularity)
        assertThat(restaurantEntity.sortingValues?.averageProductPrice).isEqualTo(sortingValues.averageProductPrice)
        assertThat(restaurantEntity.sortingValues?.deliveryCosts).isEqualTo(sortingValues.deliveryCosts)
        assertThat(restaurantEntity.sortingValues?.minCost).isEqualTo(sortingValues.minCost)
    }

    @Test
    fun `test domain data should match entity data`() {
        val restaurantEntity = RestaurantEntity(
            name = "Entity Restaurant",
            status = "open",
            sortingValues = sortingValuesEntity
        )

        val restaurant = mapper.toDomain(restaurantEntity)

        assertThat(restaurant.name).isEqualTo(restaurantEntity.name)
        assertThat(restaurant.status.toString()).isEqualTo(restaurantEntity.status)
        assertThat(restaurant.sortingValues?.bestMatch).isEqualTo(sortingValuesEntity.bestMatch)
        assertThat(restaurant.sortingValues?.newest).isEqualTo(sortingValuesEntity.newest)
        assertThat(restaurant.sortingValues?.ratingAverage).isEqualTo(sortingValuesEntity.ratingAverage)
        assertThat(restaurant.sortingValues?.distance).isEqualTo(sortingValuesEntity.distance)
        assertThat(restaurant.sortingValues?.popularity).isEqualTo(sortingValuesEntity.popularity)
        assertThat(restaurant.sortingValues?.averageProductPrice).isEqualTo(sortingValuesEntity.averageProductPrice)
        assertThat(restaurant.sortingValues?.deliveryCosts).isEqualTo(sortingValuesEntity.deliveryCosts)
        assertThat(restaurant.sortingValues?.minCost).isEqualTo(sortingValuesEntity.minCost)
    }

    @Test
    fun `test isFavorite should be true from entity`() {
        val restaurantEntity = RestaurantEntity(
            name = "Entity Restaurant",
            status = "open",
            sortingValues = sortingValuesEntity
        )

        val restaurant = mapper.toDomain(restaurantEntity)

        assertThat(restaurant.isFavorite).isTrue()
    }
}