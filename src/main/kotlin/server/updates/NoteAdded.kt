package server.updates

import server.shipment.Shipment

class NoteAdded : Update {
    override fun apply(shipment: Shipment, otherInfo: String) {
        shipment.addNote(otherInfo)
    }
}