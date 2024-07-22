package updates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import server.updates.Update
import server.shipment.Shipment
import server.shipment.StandardShipment
import server.updates.Lost
import kotlin.test.assertTrue

class LostTest {
    @Test
    fun testLostIsUpdate() {
        val lost = Lost()
        assertTrue(lost is Update, "Lost should be an Update")
    }

    @Test
    fun testLostApply() {
        val shipment = StandardShipment("s12000")
        Lost().apply(shipment, 123123, "")
        assertEquals("lost", shipment.status, "Shipment status is not what was expected")
    }
}