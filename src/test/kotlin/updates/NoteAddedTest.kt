package updates

import org.junit.jupiter.api.Test
import shipment.Shipment
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteAddedTest {
    @Test
    fun testNoteIsUpdate() {
        val note = NoteAdded()
        assertTrue(note is Update, "NoteAdded should be an Update")
    }

    @Test
    fun testNoteApply() {
        val shipment = Shipment("s12000")
        NoteAdded().apply(shipment, "This is a note")
        assertTrue(shipment.notes.contains("This is a note"), "Shipment notes do not contain the note that was added")
        assertEquals(shipment.notes.size, 1, "Shipment notes size is not what was expected")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")

        NoteAdded().apply(shipment, "This is another note")
        assertTrue(shipment.notes.contains("This is another note"), "Shipment notes do not contain the note that was added")
        assertEquals(shipment.notes.size, 2, "Shipment notes size is not what was expected")
        assertEquals("created", shipment.status, "Shipment status is not what was expected")
    }
}