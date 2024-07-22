package shipment

import org.junit.jupiter.api.assertThrows
import server.shipment.BulkShipment
import server.shipment.OvernightShipment
import server.shipment.StandardShipment
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class OvernightShipmentTest {

    @Test
    fun testOvernightShipmentConstruction() {
        val shipment = OvernightShipment("s12000")
        assertEquals("s12000", shipment.id, "Shipment id is not what was expected")
        assertEquals("", shipment.location, "Shipment location is not what was expected")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")
        assertEquals(null, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertTrue(shipment.notes.isEmpty(), "Shipment notes are not empty")
        assertTrue(shipment.updateHistory.isEmpty(), "Shipment update history is not empty")

        assertThrows<IllegalArgumentException> {
            OvernightShipment("")
        }
    }

    @Test
    fun testOvernightShipmentExpectedDeliveryDate() {
        val shipment = OvernightShipment("s12000")
        shipment.creationDateTimeStamp = 1000
        shipment.expectedDeliveryDateTimestamp = 1000
        assertEquals(1000, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertEquals(0, shipment.notes.size, "Shipment notes size is not what was expected")

        shipment.expectedDeliveryDateTimestamp = 1000 + 24 * 60 * 60 * 1000
        assertEquals(1000 + 24 * 60 * 60 * 1000, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertEquals(1, shipment.notes.size, "Shipment notes size is not what was expected")
        assertEquals("Expected delivery date must be 1 day after creation date", shipment.notes[0], "Shipment note is not what was expected")
    }
}