package server.shipment

interface ShipmentObserver {
    fun notify(shipment: Shipment)
}