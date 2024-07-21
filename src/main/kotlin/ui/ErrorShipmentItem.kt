package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.onClick
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun ErrorShipmentItem(shipmentId: String) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(10.dp).background(color = Color(179, 174, 173))
    ) {
        Text("Shipment with id $shipmentId not found", modifier = Modifier.padding(10.dp))
    }
}