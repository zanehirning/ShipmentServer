package server.updates

import server.shipment.Shipment

class Lost : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.status = "lost"
    }
}