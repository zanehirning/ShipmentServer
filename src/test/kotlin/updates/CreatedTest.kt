package updates

import shipment.Shipment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CreatedTest {
    @Test
    fun testCreatedIsUpdate() {
        val created = Created()
        assertTrue(created is Update, "Created is not an Update")
    }

    @Test
    fun testCreatedApply() {
        val shipment = Shipment("s12000")
        Created().apply(shipment, "")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")
    }
}