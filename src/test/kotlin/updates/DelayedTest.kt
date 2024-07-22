package updates

import org.junit.jupiter.api.Assertions.assertEquals
import server.updates.Update
import server.shipment.Shipment
import server.shipment.StandardShipment
import server.updates.Delayed
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
        val shipment = StandardShipment("s12000")
        Delayed().apply(shipment, 123123, "12301923")
        assertEquals("delayed", shipment.status, "Shipment status is not what was expected")
        assertEquals(12301923L, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date timestamp is not what was expected")
    }
}