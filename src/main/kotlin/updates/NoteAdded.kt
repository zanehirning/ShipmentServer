package updates

import shipment.Shipment

class NoteAdded : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.addNote(otherInfo)
    }
}