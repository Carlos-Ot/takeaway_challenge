package com.example.coredata.enum

import com.example.coredata.domain.model.enums.OpeningStatus
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class OpeningStatusTest {

    @Test
    fun `test safeValueOf should be null for invalid values`() {
        val status = "opened"

        val openingStatus = OpeningStatus.safeValueOf(status)

        assertThat(openingStatus).isNull()
    }

    @Test
    fun `test safeValueOf should return an OpeningStatus value`() {
        val status = "open"

        val openingStatus = OpeningStatus.safeValueOf(status)

        assertThat(openingStatus).isNotNull()
        assertThat(openingStatus).isInstanceOf(OpeningStatus::class.java)
        assertThat(openingStatus.toString()).isEqualTo(status)
    }
}