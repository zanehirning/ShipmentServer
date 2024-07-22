package server.shipment

class BulkShipment(shipmentId: String) : Shipment(shipmentId){
    override var expectedDeliveryDateTimestamp: Long? = null
        set(value) {
            if (value != null && creationDateTimeStamp != null) {
                if (value - creationDateTimeStamp!! <= 3 * 24 * 60 * 60 * 1000) {
                    addNote("Expected delivery date must be at least 3 days after creation date")
                }
            }
            field = value
        }
}