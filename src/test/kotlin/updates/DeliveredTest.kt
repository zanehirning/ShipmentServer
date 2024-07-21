package updates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import shipment.Shipment
import kotlin.test.assertTrue

class DeliveredTest {
    @Test
    fun testDeliveredIsUpdate() {
        val delivered = Delivered()
        assertTrue(delivered is Update, "Delivered should be an Update")
    }

    @Test
    fun testDeliveredApply() {
        val shipment = Shipment("s12000")
        Delivered().apply(shipment, "")
        assertEquals("delivered", shipment.status, "Shipment status is not what was expected")
    }
}