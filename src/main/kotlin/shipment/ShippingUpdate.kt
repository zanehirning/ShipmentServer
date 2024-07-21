package shipment

class ShippingUpdate(
    val previousStatus: String,
    val newStatus: String,
    val timestamp: Long
)