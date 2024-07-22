package server.updates

import server.controllers.TrackingController
import server.shipment.Shipment

class Created : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.status = "created"
        shipment.creationDateTimeStamp = timeStampOfUpdate
        TrackingController.addShipment(shipment)
    }
}