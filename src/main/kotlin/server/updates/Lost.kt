package server.updates

import server.shipment.Shipment

class Lost : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.status = "lost"
        shipment.addUpdate(timeStampOfUpdate)
    }
}