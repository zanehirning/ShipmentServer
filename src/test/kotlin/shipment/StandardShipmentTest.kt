package shipment

import org.junit.jupiter.api.assertThrows
import server.shipment.Shipment
import server.shipment.ShipmentObserver
import server.shipment.StandardShipment
import server.updates.Created
import server.updates.Shipped
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Tests for the StandardShipment class
 * This also tests abstract shipment behavior as StandardShipment is the default Shipment
 */
class StandardShipmentTest {
    @Test
    fun testShipmentConstruction() {
        val shipment = StandardShipment("s12000")
        assertEquals("s12000", shipment.id, "Shipment id is not what was expected")
        assertEquals("", shipment.location, "Shipment location is not what was expected")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")
        assertEquals(null, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertTrue(shipment.notes.isEmpty(), "Shipment notes are not empty")
        assertTrue(shipment.updateHistory.isEmpty(), "Shipment update history is not empty")

        assertThrows<IllegalArgumentException> {
            StandardShipment("")
        }
    }

    @Test
    fun testAddUpdate() {
        val shipment = StandardShipment("s12000")
        Created().apply(shipment, 1234567890, "")
        shipment.addUpdate(1234567890)
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals("created", shipment.updateHistory[0].previousStatus, "Shipment update history previous status is not what was expected")

        Shipped().apply(shipment, 1234567890, "123123")
        assertEquals(2, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals("created", shipment.updateHistory[1].previousStatus, "Shipment update history previous status is not what was expected")
        assertEquals("shipped", shipment.updateHistory[1].newStatus, "Shipment update history previous status is not what was expected")
    }

    @Test
    fun testAddNote() {
        val shipment = StandardShipment("s12000")
        shipment.addNote("This is a note")
        assertEquals(1, shipment.notes.size, "Shipment notes size is not what was expected")
        assertEquals("This is a note", shipment.notes[0], "Shipment note is not what was expected")
        shipment.addNote("This is another note")
        assertEquals(2, shipment.notes.size, "Shipment notes size is not what was expected")
        assertEquals("This is another note", shipment.notes[1], "Shipment note is not what was expected")
        shipment.addNote("")
        assertEquals(3, shipment.notes.size, "Shipment notes size is not what was expected")
        assertEquals("", shipment.notes[2], "Shipment note is not what was expected")
    }

    @Test
    fun testSubscribe() {
        val shipment = StandardShipment("s12000")
        // Create an observer and test that notify is called
        val observer = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.expectedDeliveryDateTimestamp = 120
            }
        }
        val observer2 = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.creationDateTimeStamp = 1234567890
            }
        }
        shipment.subscribe(observer)
        shipment.subscribe(observer2)
        shipment.status = "shipped"
        assertEquals(120, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertEquals(1234567890, shipment.creationDateTimeStamp, "Shipment creation date is not what was expected")
    }

    @Test
    fun testUnsubscribe() {
        val shipment = StandardShipment("s12000")
        // Create an observer and test that notify is called
        val observer = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.expectedDeliveryDateTimestamp = 120
            }
        }
        shipment.subscribe(observer)
        shipment.unsubscribe(observer)
        shipment.status = "delivered"
        assertEquals(null, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
    }

    @Test
    fun testNotifyObserver() {
        val shipment = StandardShipment("s12000")
        // Create an observer and test that notify is called
        val observer = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.expectedDeliveryDateTimestamp = 120
                assertEquals(120, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
            }
        }
        val observer2 = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.creationDateTimeStamp = 1234567890
                assertEquals(1234567890, shipment.creationDateTimeStamp, "Shipment creation date is not what was expected")
            }
        }
        shipment.subscribe(observer)
        shipment.subscribe(observer2)
        shipment.notifyObserver()
    }
}