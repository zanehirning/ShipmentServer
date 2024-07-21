package updates

import org.junit.jupiter.api.Assertions.assertEquals
import shipment.Shipment
import kotlin.test.Test
import kotlin.test.assertTrue

class LocationTest {
    @Test
    fun testLocationIsUpdate() {
        val location = Location()
        assertTrue(location is Update, "Location should be an Update")
    }

    @Test
    fun testLocationApply() {
        val shipment = Shipment("s12000")
        Location().apply(shipment, "New York")
        assertEquals("New York", shipment.location, "Shipment location is not what was expected")
        kotlin.test.assertEquals("created", shipment.status, "Shipment status is not what was expected")
    }
}