package com.ottoboni.corelocalstorage.database.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tb_restaurant", indices = [Index(value = ["name"], unique = true)])
data class RestaurantEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val status: String,
    @Embedded val sortingValues: SortingValuesEntity?,
)

data class SortingValuesEntity(
    @ColumnInfo(name = "best_match")
    val bestMatch: Float,
    @ColumnInfo(name = "newest")
    val newest: Float,
    @ColumnInfo(name = "rating_average")
    val ratingAverage: Float,
    @ColumnInfo(name = "distance")
    val distance: Long,
    @ColumnInfo(name = "popularity")
    val popularity: Float,
    @ColumnInfo(name = "average_product_price")
    val averageProductPrice: Long,
    @ColumnInfo(name = "delivery_costs")
    val deliveryCosts: Long,
    @ColumnInfo(name = "min_cost")
    val minCost: Long,
)
