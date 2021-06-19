package com.ottoboni.takeawaychallenge.coredata.factory

import com.ottoboni.takeawaychallenge.coredata.domain.factory.OpeningStatusFactory
import com.ottoboni.takeawaychallenge.coredata.domain.factory.RestaurantFactory
import com.ottoboni.takeawaychallenge.coredata.domain.factory.SortingValuesFactory
import com.google.common.truth.Truth.assertThat
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.RestaurantData
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.SortingValuesData
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.enums.OpeningStatusData
import org.junit.Test

class RestaurantFactoryTest {

    private val sortingValuesData = SortingValuesData(
        bestMatch = 1.0f,
        newest = 10.0f,
        ratingAverage = 7.0f,
        distance = 1000,
        popularity = 4.5f,
        averageProductPrice = 2000,
        deliveryCosts = 1000,
        minCost = 1500
    )

    private val factory = RestaurantFactory(OpeningStatusFactory(), SortingValuesFactory())

    @Test
    fun `test empty values should be turned to null`() {
        val restaurantData = RestaurantData(
            name = " ",
            status = OpeningStatusData.OPEN,
            sortingValues = null
        )


        val restaurant = factory.make(restaurantData)

        assertThat(restaurant.name).isNull()
        assertThat(restaurant.sortingValues).isNull()
    }

    @Test
    fun `test non-empty values should not change`() {
        val restaurantData = RestaurantData(
            name = "Test Restaurant",
            status = OpeningStatusData.OPEN,
            sortingValues = sortingValuesData
        )

        val restaurant = factory.make(restaurantData)

        assertThat(restaurant.name).isEqualTo(restaurantData.name)
        assertThat(restaurant.status.toString()
            .uppercase()).isEqualTo(restaurantData.status.toString())
        assertThat(restaurant.sortingValues?.bestMatch).isEqualTo(sortingValuesData.bestMatch)
        assertThat(restaurant.sortingValues?.newest).isEqualTo(sortingValuesData.newest)
        assertThat(restaurant.sortingValues?.ratingAverage).isEqualTo(sortingValuesData.ratingAverage)
        assertThat(restaurant.sortingValues?.distance).isEqualTo(sortingValuesData.distance)
        assertThat(restaurant.sortingValues?.popularity).isEqualTo(sortingValuesData.popularity)
        assertThat(restaurant.sortingValues?.averageProductPrice).isEqualTo(sortingValuesData.averageProductPrice)
        assertThat(restaurant.sortingValues?.deliveryCosts).isEqualTo(sortingValuesData.deliveryCosts)
        assertThat(restaurant.sortingValues?.minCost).isEqualTo(sortingValuesData.minCost)
    }

    @Test
    fun `test isFavorite should be false`() {
        val restaurantData = RestaurantData(
            name = "Test Restaurant",
            status = OpeningStatusData.OPEN,
            sortingValues = sortingValuesData
        )

        val restaurant = factory.make(restaurantData)

        assertThat(restaurant.isFavorite).isFalse()
    }
}