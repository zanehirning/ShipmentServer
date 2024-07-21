package server.viewmodels

import server.shipment.Shipment
import server.shipment.ShipmentObserver
import server.TrackingSimulator
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue

class TrackerViewModelState {
    var shipmentTextFieldText by mutableStateOf(TextFieldValue(""))
    var trackedShipmentIds = mutableStateListOf<String>()
}

class TrackerViewModel : ShipmentObserver {
    val uiState = TrackerViewModelState()

    override fun notify(shipment: Shipment) {
        uiState.trackedShipmentIds.indexOf(shipment.id).takeIf { it != -1 }?.let {
            uiState.trackedShipmentIds[it] = ""
            uiState.trackedShipmentIds[it] = shipment.id
        }
    }

    fun startTrackingShipment(shipmentId: String) {
        uiState.trackedShipmentIds.add(shipmentId)
        TrackingSimulator.findShipment(shipmentId)?.subscribe(this)
    }

    fun stopTrackingShipment(shipment: Shipment) {
        shipment.unsubscribe(this)
        if (uiState.trackedShipmentIds.contains(shipment.id)) {
            uiState.trackedShipmentIds.remove(shipment.id)
        }
    }
}