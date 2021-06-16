package com.ottoboni.corelocalstorage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ottoboni.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.corelocalstorage.database.dao.UserDao
import com.ottoboni.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.corelocalstorage.database.entity.RestaurantEntity
import com.ottoboni.corelocalstorage.database.entity.UserEntity
import com.ottoboni.corelocalstorage.database.entity.UserRestaurantEntity

@Database(
    entities = [
        RestaurantEntity::class,
        UserEntity::class,
        UserRestaurantEntity::class
    ],
    version = AppDatabase.VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao
    abstract fun userDao(): UserDao
    abstract fun userRestaurantDao(): UserRestaurantDao

    companion object {
        const val NAME = "app_database.db"
        const val VERSION = 1

        fun buildDatabase(context: Context, inMemory: Boolean = false): AppDatabase =
            if (inMemory)
                Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
            else
                Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                    .build()
    }
}