package shipment

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import server.shipment.ExpressShipment
import kotlin.test.assertTrue

class ExpressShipmentTest {
    @Test
    fun testExpressShipmentConstruction() {
        val shipment = ExpressShipment("s12000")
        assertEquals("s12000", shipment.id, "Shipment id is not what was expected")
        assertEquals("", shipment.location, "Shipment location is not what was expected")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")
        assertEquals(null, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertTrue(shipment.notes.isEmpty(), "Shipment notes are not empty")
        assertTrue(shipment.updateHistory.isEmpty(), "Shipment update history is not empty")

        assertThrows<IllegalArgumentException> {
            ExpressShipment("")
        }
    }

    @Test
    fun testExpressShipmentExpectedDeliveryDate() {
        val shipment = ExpressShipment("s12000")
        shipment.creationDateTimeStamp = 1000
        shipment.expectedDeliveryDateTimestamp = 1000
        assertEquals(1000, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertEquals(0, shipment.notes.size, "Shipment notes size is not what was expected")

        shipment.expectedDeliveryDateTimestamp = 1000 + 3 * 24 * 60 * 60 * 1000
        assertEquals(1000 + 3 * 24 * 60 * 60 * 1000, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertEquals(1, shipment.notes.size, "Shipment notes size is not what was expected")
        assertEquals("Expected delivery date cannot be more than 3 days after creation date", shipment.notes[0], "Shipment note is not what was expected")
    }
}