package com.ottoboni.corelocalstorage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ottoboni.corelocalstorage.database.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: UserEntity): Long

    @Query("SELECT * FROM tb_user WHERE id = :userId")
    suspend fun selectBy(userId: Long): UserEntity?

    @Query("DELETE FROM tb_user WHERE id = :userId")
    suspend fun deleteBy(userId: Long)
}
