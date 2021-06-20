package com.ottoboni.takeawaychallenge.corelocalstorage.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.RestaurantEntity

@Dao
interface RestaurantDao {
    /* ktlint-disable max-line-length */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(restaurant: RestaurantEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg restaurant: RestaurantEntity): List<Long>

    @Query("SELECT * FROM tb_restaurant WHERE id = :id")
    suspend fun selectBy(id: Long): RestaurantEntity?

    @Query("SELECT id FROM tb_restaurant WHERE name = :name")
    suspend fun selectIdBy(name: String): Long?

    @Query("SELECT * FROM tb_restaurant")
    suspend fun selectAll(): List<RestaurantEntity>?

    @Query(
        "DELETE FROM tb_restaurant WHERE id = :id AND :id NOT IN (SELECT restaurantId FROM tb_user_restaurant)"
    )
    suspend fun deleteSafelyBy(id: Long)

    @Query("DELETE FROM tb_restaurant")
    suspend fun deleteAll()

    @Suppress("MaxLineLength")
    @Query(
        "SELECT R.id, R.name, R.status, R.best_match, R.newest, R.rating_average, R.distance, R.popularity, R.average_product_price, R.delivery_costs, R.min_cost  FROM tb_restaurant AS R INNER JOIN tb_user_restaurant AS UR ON R.id = UR.restaurantId WHERE UR.userId = :userId AND R.name = :restaurantName"
    )
    fun observeUserRestaurantBy(userId: Long, restaurantName: String): LiveData<RestaurantEntity?>
    /* ktlint-enable max-line-length */
}
