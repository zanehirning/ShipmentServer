package server.shipment

class OvernightShipment(shipmentId: String) : Shipment(shipmentId){
    override var expectedDeliveryDateTimestamp: Long = 0
        set(value) {
            val creationDate = updateHistory.first().timestamp
            if (value - creationDate > 24 * 60 * 60 * 1000) {
                addNote("Expected delivery date must be 1 day after creation date")
            }
            field = value
        }
}
