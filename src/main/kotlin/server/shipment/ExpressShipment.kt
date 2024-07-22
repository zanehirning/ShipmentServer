package server.shipment

class ExpressShipment(shipmentId: String) : Shipment(shipmentId){
    override var expectedDeliveryDateTimestamp: Long? = null
        set(value) {
            if (value != null && creationDateTimeStamp != null) {
                if (value - creationDateTimeStamp!! >= 3 * 24 * 60 * 60 * 1000) {
                    addNote("Expected delivery date cannot be more than 3 days after creation date")
                }
            }
            field = value
        }
}
