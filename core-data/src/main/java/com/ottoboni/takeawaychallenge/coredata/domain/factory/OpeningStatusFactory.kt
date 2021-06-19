package com.ottoboni.takeawaychallenge.coredata.domain.factory

import com.ottoboni.takeawaychallenge.coredata.domain.model.enums.OpeningStatus
import com.ottoboni.takeawaychallenge.corelocalstorage.filestore.data.enums.OpeningStatusData

class OpeningStatusFactory : ModelFactory<OpeningStatusData, OpeningStatus> {
    override fun make(input: OpeningStatusData) = when (input) {
        OpeningStatusData.OPEN -> OpeningStatus.OPEN
        OpeningStatusData.CLOSED -> OpeningStatus.CLOSED
        OpeningStatusData.ORDER_AHEAD -> OpeningStatus.ORDER_AHEAD
    }
}