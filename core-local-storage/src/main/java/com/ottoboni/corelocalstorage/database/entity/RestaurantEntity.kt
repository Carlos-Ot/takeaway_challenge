package com.ottoboni.corelocalstorage.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "tb_restaurant", indices = [Index(value = ["name"], unique = true)])
data class RestaurantEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)
