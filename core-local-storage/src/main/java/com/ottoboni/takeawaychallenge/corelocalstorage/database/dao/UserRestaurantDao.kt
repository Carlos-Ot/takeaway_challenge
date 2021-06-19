package com.ottoboni.takeawaychallenge.corelocalstorage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.UserRestaurantEntity

@Dao
interface UserRestaurantDao {
    /* ktlint-disable max-line-length */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userRestaurant: UserRestaurantEntity)

    @Delete
    suspend fun delete(userRestaurant: UserRestaurantEntity)

    @Query(
        "SELECT * FROM tb_user_restaurant WHERE userId = :userId AND restaurantId = :restaurantId"
    )
    suspend fun selectBy(userId: Long, restaurantId: Long): UserRestaurantEntity?
    /* ktlint-enable max-line-length */
}
