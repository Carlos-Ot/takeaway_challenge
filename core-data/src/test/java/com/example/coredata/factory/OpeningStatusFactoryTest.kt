package com.example.coredata.factory

import com.example.coredata.domain.factory.OpeningStatusFactory
import com.example.coredata.domain.model.enums.OpeningStatus
import com.google.common.truth.Truth.assertThat
import com.ottoboni.corelocalstorage.filestore.data.enums.OpeningStatusData
import org.junit.Test

class OpeningStatusFactoryTest {

    private val factory = OpeningStatusFactory()

    @Test
    fun `test openingStatus should be OPEN`() {
        val openingStatusData = OpeningStatusData.OPEN

        val openingStatus = factory.make(openingStatusData)

        assertThat(openingStatus).isEqualTo(OpeningStatus.OPEN)
    }

    @Test
    fun `test openingStatus should be CLOSED`() {
        val openingStatusData = OpeningStatusData.CLOSED

        val openingStatus = factory.make(openingStatusData)

        assertThat(openingStatus).isEqualTo(OpeningStatus.CLOSED)
    }

    @Test
    fun `test openingStatus should be ORDER_AHEAD`() {
        val openingStatusData = OpeningStatusData.ORDER_AHEAD

        val openingStatus = factory.make(openingStatusData)

        assertThat(openingStatus).isEqualTo(OpeningStatus.ORDER_AHEAD)
    }
}