import shipment.Shipment
import updates.*

object TrackingSimulator {
    private val updateOperations: Map<String, Update> = mapOf(
        "created" to Created(),
        "canceled" to Canceled(),
        "delayed" to Delayed(),
        "delivered" to Delivered(),
        "location" to Location(),
        "lost" to Lost(),
        "noteadded" to NoteAdded(),
        "shipped" to Shipped()
    )
    private var shipments: List<Shipment> = listOf()
    private var parser: CsvParser = CsvParser("src/main/kotlin/input/test.txt")

    fun runSimulation() {
        val line = parser.parseLine()
        when (line.size) {
            3 -> {
                val status = line[0]
                val shipmentId = line[1]
                val timeStampOfUpdate = line[2]
                update(shipmentId, status, timeStampOfUpdate)
            }
            4 -> {
                val status = line[0]
                val shipmentId = line[1]
                val timeStampOfUpdate = line[2]
                val otherInfo = line.slice(3 until line.size).joinToString(",")
                update(shipmentId, status, timeStampOfUpdate, otherInfo)
            }
        }
    }

    fun findShipment(id: String): Shipment? {
        return shipments.find { it.id == id }
    }

    fun addShipment(shipment: Shipment) {
        shipments += shipment
    }

    private fun update(shipmentId: String, operation: String, timeStampOfUpdate: String, otherInfo: String = "") {
        val shipment = findShipment(shipmentId) ?: Shipment(shipmentId)
        if (operation in updateOperations) {
            shipment.addUpdate(updateOperations[operation]!!, timeStampOfUpdate, otherInfo)
        }
    }
}