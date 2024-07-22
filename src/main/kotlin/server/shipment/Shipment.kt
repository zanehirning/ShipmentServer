package server.shipment

abstract class Shipment(
    val id: String,
) : ShipmentSubject {
    init {
        if (id.isEmpty()) {
            throw IllegalArgumentException("Shipment id cannot be empty")
        }
    }
    var status: String = "created"
        set(value) {
            field = value
            notifyObserver()
        }
    var location: String = ""
        set(value) {
            field = value
            notifyObserver()
        }
    var notes = mutableListOf<String>()
        private set(value) {
            field = value
            notifyObserver()
        }
    var updateHistory = mutableListOf<ShippingUpdate>()
        private set
    var creationDateTimeStamp: Long? = null
    open var expectedDeliveryDateTimestamp: Long? = null
    private val subscribers = mutableListOf<ShipmentObserver>()

    fun addUpdate(timeStampOfUpdate: Long) {
        updateHistory += ShippingUpdate(
            previousStatus = updateHistory.lastOrNull()?.newStatus ?: "created",
            newStatus = this.status,
            timestamp = timeStampOfUpdate
        )
    }

    fun addNote(note: String) {
        val newNotes = notes.toMutableList()
        newNotes.add(note)
        notes = newNotes
    }

    override fun subscribe(observer: ShipmentObserver) {
        subscribers.add(observer)
    }

    override fun unsubscribe(observer: ShipmentObserver) {
        subscribers.remove(observer)
    }

    override fun notifyObserver() {
        subscribers.forEach {
            it.notify(this)
        }
    }
}