package server.shipment

class ExpressShipment(shipmentId: String) : Shipment(shipmentId){
    override var expectedDeliveryDateTimestamp: Long = 0
        set(value) {
            updateHistory
            val creationDate = updateHistory.first().timestamp
            if (value - creationDate > 3 * 24 * 60 * 60 * 1000) {
                addNote("Expected delivery date cannot be more than 3 days after creation date")
            }
            field = value
        }
}
