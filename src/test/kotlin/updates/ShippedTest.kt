package updates

import org.junit.jupiter.api.Test
import server.updates.Update
import server.shipment.Shipment
import server.shipment.StandardShipment
import server.updates.Shipped
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
        val shipment = StandardShipment("s12000")
        Shipped().apply(shipment, 123123, "23104812")
        assertEquals("shipped", shipment.status, "Shipment status is not what was expected")
        assertEquals("23104812", shipment.expectedDeliveryDateTimestamp.toString(), "Shipment expected delivery date is not what was expected")
    }
}