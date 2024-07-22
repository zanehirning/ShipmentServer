package shipment

import org.junit.jupiter.api.Test
import server.enums.ShipmentType
import server.shipment.*

class DispatcherTest {
    @Test
    fun testDispatcherCreateShipment() {
        val dispatcher = Dispatcher()
        val shipment = dispatcher.createShipment(ShipmentType.STANDARD, "s12000")
        assert(shipment is StandardShipment)
        assert(shipment.id == "s12000")

        val shipment2 = dispatcher.createShipment(ShipmentType.EXPRESS, "s12001")
        assert(shipment2 is ExpressShipment)
        assert(shipment2.id == "s12001")

        val shipment3 = dispatcher.createShipment(ShipmentType.OVERNIGHT, "s12002")
        assert(shipment3 is OvernightShipment)
        assert(shipment3.id == "s12002")

        val shipment4 = dispatcher.createShipment(ShipmentType.BULK, "s12003")
        assert(shipment4 is BulkShipment)
        assert(shipment4.id == "s12003")
    }
}