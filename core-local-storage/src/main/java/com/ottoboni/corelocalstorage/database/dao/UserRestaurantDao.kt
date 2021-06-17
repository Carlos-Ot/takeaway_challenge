package com.ottoboni.corelocalstorage.database.dao

import androidx.room.*
import com.ottoboni.corelocalstorage.database.entity.UserRestaurantEntity

@Dao
interface UserRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userRestaurant: UserRestaurantEntity)

    @Delete
    suspend fun delete(userRestaurant: UserRestaurantEntity)

    @Query("SELECT * FROM tb_user_restaurant WHERE userId = :userId AND restaurantId = :restaurantId")
    suspend fun getBy(userId: Long, restaurantId: Long): UserRestaurantEntity?
}