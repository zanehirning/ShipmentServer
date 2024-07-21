package server.shipment

class BulkShipment(shipmentId: String) : Shipment(shipmentId){
    override var expectedDeliveryDateTimestamp: Long = 0
        set(value) {
            val creationDate = updateHistory.first().timestamp
            if (value - creationDate < 3 * 24 * 60 * 60 * 1000) {
                addNote("Expected delivery date must be at least 3 days after creation date")
            }
            field = value
        }
}