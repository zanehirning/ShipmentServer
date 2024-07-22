package server.updates

import server.shipment.Shipment

interface Update {
    fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String)
}