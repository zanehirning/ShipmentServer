package server.shipment

class OvernightShipment(shipmentId: String) : Shipment(shipmentId){
    override var expectedDeliveryDateTimestamp: Long? = null
        set(value) {
            if (value != null && creationDateTimeStamp != null) {
                if (value - creationDateTimeStamp!! > 24 * 60 * 60 * 1000) {
                    addNote("Expected delivery date must be 1 day after creation date")
                }
            }
            field = value
        }
}
