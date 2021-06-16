package com.ottoboni.corelocalstorage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurant: RestaurantEntity)

    @Query("SELECT * FROM tb_restaurant WHERE id = :id")
    suspend fun selectBy(id: Long): RestaurantEntity?

    @Query("DELETE FROM tb_restaurant WHERE id = :id AND :id NOT IN (SELECT restaurantId FROM tb_user_restaurant)")
    suspend fun deleteSafelyBy(id: Long)
}