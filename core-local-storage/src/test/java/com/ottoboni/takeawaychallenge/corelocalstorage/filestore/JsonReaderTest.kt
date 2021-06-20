package com.ottoboni.takeawaychallenge.corelocalstorage.filestore

import android.content.Context
import com.squareup.moshi.Moshi
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import java.io.IOException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class JsonReaderTest {

    @RelaxedMockK
    private lateinit var context: Context

    @RelaxedMockK
    private lateinit var moshi: Moshi

    @InjectMockKs
    private lateinit var jsonReader: JsonReaderImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test(expected = IOException::class)
    fun `test readRestaurantData should throw exception when open file is error`() =
        runBlockingTest {
            every { context.assets.open(any(), any()) } throws IOException()

            jsonReader.readRestaurantData("")
        }
}
