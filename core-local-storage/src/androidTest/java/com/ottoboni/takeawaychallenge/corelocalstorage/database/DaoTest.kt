package com.ottoboni.takeawaychallenge.corelocalstorage.database

import androidx.test.core.app.ApplicationProvider
import org.junit.AfterClass
import org.junit.BeforeClass

abstract class DaoTest {

    companion object {

        @JvmStatic
        protected lateinit var appDatabase: AppDatabase

        @BeforeClass
        @JvmStatic
        fun initDb() {
            appDatabase =
                AppDatabase
                    .buildDatabase(
                        ApplicationProvider.getApplicationContext(),
                        inMemory = true
                    )
        }

        @AfterClass
        @JvmStatic
        fun closeDb() {
            appDatabase.close()
        }
    }
}
