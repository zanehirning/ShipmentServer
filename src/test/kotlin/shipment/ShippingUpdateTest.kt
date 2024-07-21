package shipment

import org.junit.jupiter.api.Test

class ShippingUpdateTest {
    @Test
    fun testShippingUpdateConstruction() {
        val shippingUpdate = ShippingUpdate(
            previousStatus = "created",
            newStatus = "shipped",
            timestamp = 1234567890
        )
        assert(shippingUpdate.previousStatus == "created")
        assert(shippingUpdate.newStatus == "shipped")
        assert(shippingUpdate.timestamp == 1234567890L)

        val shippingUpdate2 = ShippingUpdate(
            previousStatus = "shipped",
            newStatus = "delivered",
            timestamp = 1234567890
        )
        assert(shippingUpdate2.previousStatus == "shipped")
        assert(shippingUpdate2.newStatus == "delivered")
        assert(shippingUpdate2.timestamp == 1234567890L)
    }
}