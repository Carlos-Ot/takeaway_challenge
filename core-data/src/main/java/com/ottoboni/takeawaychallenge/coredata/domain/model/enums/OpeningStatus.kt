package com.ottoboni.takeawaychallenge.coredata.domain.model.enums

import java.lang.Exception

enum class OpeningStatus {
    OPEN,
    CLOSED,
    ORDER_AHEAD;

    override fun toString(): String = this.name.lowercase()

    companion object {
        fun safeValueOf(type: String): OpeningStatus? =
            try {
                valueOf(type.uppercase())
            } catch (e: Exception) {
                null
            }
    }
}