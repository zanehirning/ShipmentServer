package updates

import org.junit.jupiter.api.Assertions.assertEquals
import shipment.Shipment
import kotlin.test.Test
import kotlin.test.assertTrue

class DelayedTest {
    @Test
    fun testDelayedIsUpdate() {
        val delayed = Delayed()
        assertTrue(delayed is Update, "Delayed is not an Update")
    }

    @Test
    fun testDelayedApply() {
        val shipment = Shipment("s12000")
        Delayed().apply(shipment, "12301923")
        assertEquals("delayed", shipment.status, "Shipment status is not what was expected")
        assertEquals(12301923L, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date timestamp is not what was expected")
    }
}