package updates

import org.junit.jupiter.api.Assertions.assertEquals
import shipment.Shipment
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
        val shipment = Shipment("s12000")
        Canceled().apply(shipment, "")
        assertEquals("canceled", shipment.status, "Shipment status is not what was expected")
    }
}