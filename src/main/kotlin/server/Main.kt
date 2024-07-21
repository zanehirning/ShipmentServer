package server

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import server.controllers.TrackingController
import server.ui.ErrorShipmentItem
import server.ui.ShipmentItem
import server.viewmodels.TrackerViewModel

@Composable
@Preview
fun App() {
    val viewModel = TrackerViewModel()
    val state = viewModel.uiState

    MaterialTheme {
        LaunchedEffect(true) {
            async { TrackingServer }
        }
        Column(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = state.shipmentTextFieldText,
                    onValueChange = {
                        if (it.text.contains("\n")) {
                            viewModel.startTrackingShipment(state.shipmentTextFieldText.text)
                            state.shipmentTextFieldText = TextFieldValue("")
                        }
                        else {
                            state.shipmentTextFieldText = it
                        }
                    },
                    placeholder = { Text("Enter Shipment Id") }
                )
                Button(onClick = {
                    viewModel.startTrackingShipment(state.shipmentTextFieldText.text)
                    state.shipmentTextFieldText = TextFieldValue("")
                }) {
                    Text("Search")
                }
            }
            state.trackedShipmentIds.reversed().forEach {
                val shipment = TrackingController.findShipment(it)
                if (shipment != null) {
                    ShipmentItem(
                        shipment,
                        onShipmentClose = {
                            viewModel.stopTrackingShipment(shipment)
                        }
                    )
                }
                else {
                    ErrorShipmentItem(it)
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
