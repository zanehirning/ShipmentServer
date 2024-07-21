package updates

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import shipment.Shipment
import kotlin.test.assertTrue

class LostTest {
    @Test
    fun testLostIsUpdate() {
        val lost = Lost()
        assertTrue(lost is Update, "Lost should be an Update")
    }

    @Test
    fun testLostApply() {
        val shipment = Shipment("s12000")
        Lost().apply(shipment, "")
        assertEquals("lost", shipment.status, "Shipment status is not what was expected")
    }
}