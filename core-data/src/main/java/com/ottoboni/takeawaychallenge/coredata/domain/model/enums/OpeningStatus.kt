package com.ottoboni.takeawaychallenge.coredata.domain.model.enums

enum class OpeningStatus {
    OPEN,
    CLOSED,
    ORDER_AHEAD;

    override fun toString(): String = this.name.lowercase()

    companion object {
        fun safeValueOf(type: String): OpeningStatus? =
            try {
                valueOf(type.uppercase())
            } catch (e: IllegalArgumentException) {
                null
            }
    }
}
