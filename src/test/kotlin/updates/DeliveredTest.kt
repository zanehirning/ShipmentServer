package updates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import server.updates.Delivered
import server.updates.Update
import server.shipment.Shipment
import server.shipment.StandardShipment
import kotlin.test.assertTrue

class DeliveredTest {
    @Test
    fun testDeliveredIsUpdate() {
        val delivered = Delivered()
        assertTrue(delivered is Update, "Delivered should be an Update")
    }

    @Test
    fun testDeliveredApply() {
        val shipment = StandardShipment("s12000")
        Delivered().apply(shipment, 123123, "")
        assertEquals("delivered", shipment.status, "Shipment status is not what was expected")
    }
}