package server.updates

import server.shipment.Shipment

class NoteAdded : Update {
    override fun apply(shipment: Shipment, timeStampOfUpdate: Long, otherInfo: String) {
        shipment.addNote(otherInfo)
    }
}