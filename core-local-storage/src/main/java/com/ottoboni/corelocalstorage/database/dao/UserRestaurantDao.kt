package com.ottoboni.corelocalstorage.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ottoboni.corelocalstorage.database.entity.UserRestaurantEntity

@Dao
interface UserRestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userRestaurant: UserRestaurantEntity)

    @Delete
    suspend fun delete(userRestaurant: UserRestaurantEntity)
}