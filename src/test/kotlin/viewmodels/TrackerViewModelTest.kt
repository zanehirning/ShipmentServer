package viewmodels

import TrackingSimulator
import org.junit.jupiter.api.Test
import shipment.Shipment
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
        trackerViewModel.stopTrackingShipment(TrackingSimulator.findShipment("s12000") ?: Shipment("s12000"))
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 0)
    }

    @Test
    fun testNotify() {
        val trackerViewModel = TrackerViewModel()
        trackerViewModel.startTrackingShipment("s12000")
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 1)
        trackerViewModel.notify(TrackingSimulator.findShipment("s12000") ?: Shipment("s12000"))
        assertEquals(trackerViewModel.uiState.trackedShipmentIds.size, 1)
    }
}