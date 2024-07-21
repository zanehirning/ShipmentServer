package viewmodels

import org.junit.jupiter.api.Test
import server.controllers.TrackingController
import server.shipment.Shipment
import server.shipment.StandardShipment
import server.viewmodels.TrackerViewModel
import kotlin.test.assertEquals

class TrackerViewModelTest {
    @Test
    fun testTrackerViewModelConstruction() {
        val trackerViewModel = TrackerViewModel()
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 0)
        assertEquals(trackerViewModel.uiState.shipmentTextFieldText.text, "")
    }

    @Test
    fun testStartTrackingShipment() {
        val trackerViewModel = TrackerViewModel()
        trackerViewModel.startTrackingShipment("s12000")
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 1)
    }

    @Test
    fun testStopTrackingShipment() {
        val trackerViewModel = TrackerViewModel()
        trackerViewModel.startTrackingShipment("s12000")
        trackerViewModel.stopTrackingShipment(TrackingController.findShipment("s12000") ?: StandardShipment("s12000"))
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 0)
    }

    @Test
    fun testNotify() {
        val trackerViewModel = TrackerViewModel()
        trackerViewModel.startTrackingShipment("s12000")
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 1)
        trackerViewModel.notify(TrackingController.findShipment("s12000") ?: StandardShipment("s12000"))
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 1)
    }
}