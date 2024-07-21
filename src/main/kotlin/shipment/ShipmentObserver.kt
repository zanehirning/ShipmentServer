package shipment

interface ShipmentObserver {
    fun notify(shipment: Shipment)
}