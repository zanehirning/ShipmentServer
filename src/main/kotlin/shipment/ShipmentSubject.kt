package shipment

interface ShipmentSubject {
    fun subscribe(observer: ShipmentObserver)
    fun unsubscribe(observer: ShipmentObserver)
    fun notifyObserver()
}