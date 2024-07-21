package ui

import shipment.Shipment
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.Date

@Composable
fun ShipmentItem(shipment: Shipment, onShipmentClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp).background(color = Color(179, 174, 173))
    ) {
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Text("Tracking Shipment: ${shipment.id}", modifier = Modifier.padding(10.dp))
            IconButton(
                onClick = {
                    onShipmentClose()
                }
            ) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "Close",
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Text("Status: ${shipment.status}", modifier = Modifier.padding(horizontal = 10.dp))
        Text("Location: ${shipment.location}", modifier = Modifier.padding(horizontal = 10.dp))
        Text(
            "Expected Delivery: ${Date(shipment.expectedDeliveryDateTimestamp)}",
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Status Updates:", modifier = Modifier.padding(horizontal = 10.dp))
        shipment.updateHistory.forEach {
            Text(
                "Shipment went from ${it.previousStatus} to ${it.newStatus} on ${Date(it.timestamp)}",
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Text("Notes:", modifier = Modifier.padding(10.dp))
        shipment.notes.forEach {
            Text(it, modifier = Modifier.padding(horizontal = 10.dp))
        }
    }
}