package com.ottoboni.takeawaychallenge.corelocalstorage.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "tb_user_restaurant",
    indices = [Index(value = ["userId"]), Index(value = ["restaurantId"])],
    primaryKeys = ["userId", "restaurantId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = RestaurantEntity::class,
            parentColumns = ["id"],
            childColumns = ["restaurantId"],
        )
    ]
)
data class UserRestaurantEntity(
    val userId: Long,
    val restaurantId: Long
)
