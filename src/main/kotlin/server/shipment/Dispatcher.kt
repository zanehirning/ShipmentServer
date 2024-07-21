package server.shipment

import server.enums.ShipmentType

class Dispatcher {
    fun createShipment(type: ShipmentType, shipmentId: String) = when(type) {
        ShipmentType.BULK -> BulkShipment(shipmentId)
        ShipmentType.EXPRESS -> ExpressShipment(shipmentId)
        ShipmentType.STANDARD -> StandardShipment(shipmentId)
        ShipmentType.OVERNIGHT -> OvernightShipment(shipmentId)
    }
}