package shipment

import shipment.Shipment
import updates.*
import kotlin.reflect.KProperty
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ShipmentTest {
    @Test
    fun testShipmentConstruction() {
        val shipment = Shipment("s12000")
        assertEquals("s12000", shipment.id, "Shipment id is not what was expected")
        assertEquals("", shipment.location, "Shipment location is not what was expected")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")
        assertEquals(0, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertTrue(shipment.notes.isEmpty(), "Shipment notes are not empty")
        assertTrue(shipment.updateHistory.isEmpty(), "Shipment update history is not empty")

        val shipment2 = Shipment("")
        assertEquals("", shipment2.id, "Shipment id is not what was expected")
        assertEquals("", shipment2.location, "Shipment location is not what was expected")
        assertEquals("created", shipment2.status, "Shipment status is not what was expected")
        assertEquals(0, shipment2.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertTrue(shipment2.notes.isEmpty(), "Shipment notes are not empty")
        assertTrue(shipment2.updateHistory.isEmpty(), "Shipment update history is not empty")
    }

    @Test
    fun testAddUpdate() {
        // Status changes are tested in respective updates, this will test the update history
        val shipment = Shipment("s12000")
        shipment.addUpdate(Created(), "1234567890", "")
        assertEquals(0, shipment.updateHistory.size, "Shipment update history size is not what was expected")

        shipment.addUpdate(Shipped(), "1234567890", "23104812")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals(shipment.updateHistory.last().timestamp, 1234567890, "Shipment update history timestamp is not what was expected")
        assertEquals(shipment.updateHistory.last().previousStatus, "created", "Shipment update history last status is not what was expected")
        assertEquals(shipment.updateHistory.last().newStatus, "shipped", "Shipment update history new status is not what was expected")

        shipment.addUpdate(Location(), "1234567890", "Los Angeles")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")

        shipment.addUpdate(Location(), "1234567890", "New York")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")

        shipment.addUpdate(Created(), "1234567890", "")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")

        shipment.addUpdate(NoteAdded(), "1234567890", "This is a note")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")

        shipment.addUpdate(NoteAdded(), "1234567890", "This is another note")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")

        shipment.addUpdate(Delayed(), "1234567890", "23104812")
        assertEquals(2, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals(shipment.updateHistory.last().timestamp, 1234567890, "Shipment update history timestamp is not what was expected")
        assertEquals(shipment.updateHistory.last().previousStatus, "shipped", "Shipment update history last status is not what was expected")
        assertEquals(shipment.updateHistory.last().newStatus, "delayed", "Shipment update history new status is not what was expected")

        shipment.addUpdate(Lost(), "1234567890", "")
        assertEquals(3, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals(shipment.updateHistory.last().timestamp, 1234567890, "Shipment update history timestamp is not what was expected")
        assertEquals(shipment.updateHistory.last().previousStatus, "delayed", "Shipment update history last status is not what was expected")
        assertEquals(shipment.updateHistory.last().newStatus, "lost", "Shipment update history new status is not what was expected")

        shipment.addUpdate(Delivered(), "1234567890", "")
        assertEquals(4, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals(shipment.updateHistory.last().timestamp, 1234567890, "Shipment update history timestamp is not what was expected")
        assertEquals(shipment.updateHistory.last().previousStatus, "lost", "Shipment update history last status is not what was expected")
        assertEquals(shipment.updateHistory.last().newStatus, "delivered", "Shipment update history new status is not what was expected")

        shipment.addUpdate(Canceled(), "1234567890", "")
        assertEquals(5, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals(shipment.updateHistory.last().timestamp, 1234567890, "Shipment update history timestamp is not what was expected")
        assertEquals(shipment.updateHistory.last().previousStatus, "delivered", "Shipment update history last status is not what was expected")
        assertEquals(shipment.updateHistory.last().newStatus, "canceled", "Shipment update history new status is not what was expected")
    }

    @Test
    fun testAddNote() {
        val shipment = Shipment("s12000")
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
        val shipment = Shipment("s12000")
        // Create an observer and test that notify is called
        val observer = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.expectedDeliveryDateTimestamp = 120
            }
        }
        val observer2 = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.javaClass.getDeclaredField("updateHistory").apply {
                    isAccessible = true
                    set(shipment, mutableListOf(ShippingUpdate("created", "shipped", 1234567890)))
                }
            }
        }
        shipment.subscribe(observer)
        shipment.subscribe(observer2)
        shipment.status = "shipped"
        assertEquals(120, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
        assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")
        assertEquals(1234567890, shipment.updateHistory[0].timestamp, "Shipment update history timestamp is not what was expected")
        assertEquals("created", shipment.updateHistory[0].previousStatus, "Shipment update history previous status is not what was expected")
        assertEquals("shipped", shipment.updateHistory[0].newStatus, "Shipment update history new status is not what was expected")
    }

    @Test
    fun testUnsubscribe() {
        val shipment = Shipment("s12000")
        // Create an observer and test that notify is called
        val observer = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.expectedDeliveryDateTimestamp = 120
            }
        }
        shipment.subscribe(observer)
        shipment.unsubscribe(observer)
        shipment.status = "delivered"
        assertEquals(0, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
    }

    @Test
    fun testNotifyObserver() {
        val shipment = Shipment("s12000")
        // Create an observer and test that notify is called
        val observer = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.expectedDeliveryDateTimestamp = 120
                assertEquals(120, shipment.expectedDeliveryDateTimestamp, "Shipment expected delivery date is not what was expected")
            }
        }
        val observer2 = object : ShipmentObserver {
            override fun notify(shipment: Shipment) {
                shipment.javaClass.getDeclaredField("updateHistory").apply {
                    isAccessible = true
                    set(shipment, mutableListOf(ShippingUpdate("created", "shipped", 1234567890)))
                }
                assertEquals(1, shipment.updateHistory.size, "Shipment update history size is not what was expected")
                assertEquals(1234567890, shipment.updateHistory[0].timestamp, "Shipment update history timestamp is not what was expected")
                assertEquals("created", shipment.updateHistory[0].previousStatus, "Shipment update history previous status is not what was expected")
                assertEquals("shipped", shipment.updateHistory[0].newStatus, "Shipment update history new status is not what was expected")
            }
        }
        shipment.subscribe(observer)
        shipment.subscribe(observer2)
        shipment.notifyObserver()
    }
}