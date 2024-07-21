package updates

import org.junit.jupiter.api.Test
import shipment.Shipment
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ShippedTest {
    @Test
    fun testShippedIsUpdate() {
        val shipped = Shipped()
        assertTrue(shipped is Update, "Shipped should be an Update")
    }

    @Test
    fun testShippedApply() {
        val shipment = Shipment("s12000")
        Shipped().apply(shipment, "23104812")
        assertEquals("shipped", shipment.status, "Shipment status is not what was expected")
        assertEquals("23104812", shipment.expectedDeliveryDateTimestamp.toString(), "Shipment expected delivery date is not what was expected")
    }
}