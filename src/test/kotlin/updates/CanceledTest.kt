package updates

import org.junit.jupiter.api.Assertions.assertEquals
import server.updates.Canceled
import server.updates.Update
import server.shipment.Shipment
import server.shipment.StandardShipment
import kotlin.test.Test
import kotlin.test.assertTrue

class CanceledTest {
    @Test
    fun testCanceledIsUpdate() {
        val canceled = Canceled()
        assertTrue(canceled is Update, "Canceled is not an Update")
    }

    @Test
    fun testCanceledApply() {
        val shipment = StandardShipment("s12000")
        Canceled().apply(shipment, 123123, "")
        assertEquals("canceled", shipment.status, "Shipment status is not what was expected")
    }
}