package shipment

import updates.Update

class Shipment(
    val id: String,
) : ShipmentSubject {
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
    var expectedDeliveryDateTimestamp: Long = 0
    private val subscribers = mutableListOf<ShipmentObserver>()

    fun addUpdate(update: Update, timeStampOfUpdate: String, otherInfo: String) {
        update.apply(this, otherInfo)
        val lastStatus = if (updateHistory.isEmpty()) "created" else updateHistory.last().newStatus
        if (this.status == "created" && lastStatus != this.status) {
            this.status = lastStatus
        }
        else if (this.status != "created" && lastStatus != this.status && update.javaClass.simpleName != "Created") {
            updateHistory += ShippingUpdate(
                previousStatus = lastStatus,
                newStatus = this.status,
                timestamp = timeStampOfUpdate.toLong()
            )
        }
    }

    fun addNote(note: String) {
        notes.add(note)
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