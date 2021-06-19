package com.ottoboni.takeawaychallenge.corelocalstorage.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.RestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.dao.UserRestaurantDao
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.RestaurantEntity
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.UserEntity
import com.ottoboni.takeawaychallenge.corelocalstorage.database.entity.UserRestaurantEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

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
        private const val NAME = "app_database.db"
        const val VERSION = 1
        const val GUEST_USER_ID = 1L

        fun buildDatabase(context: Context, inMemory: Boolean = false): AppDatabase =
            if (inMemory)
                Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                    .allowMainThreadQueries()
                    .build()
            else
                Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            setupDefaultData(db)
                        }
                    })
                    .build()

        private fun setupDefaultData(db: SupportSQLiteDatabase) = object : CoroutineScope {
            override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO
        }.launch {
            db.execSQL(
                "INSERT INTO tb_user (id, name) VALUES (?, 'default_user')",
                arrayOf(GUEST_USER_ID)
            )
        }
    }
}
