import shipment.Shipment
import kotlin.test.BeforeTest
import kotlin.test.Test

class TrackingSimulatorTest {

    @BeforeTest
    fun setUp() {
        TrackingSimulator.javaClass.getDeclaredField("shipments").apply {
            isAccessible = true
            set(TrackingSimulator, mutableListOf<Shipment>())
        }
        TrackingSimulator.javaClass.getDeclaredField("parser").apply {
            isAccessible = true
            set(TrackingSimulator, CsvParser("src/main/kotlin/input/test.txt"))
        }
    }

    @Test
    fun testRunSimulation() {
        for (i in 0..9) {
            TrackingSimulator.runSimulation()
            val shipment = TrackingSimulator.findShipment("s1000$i")
            assert(shipment != null)
            assert(shipment?.status == "created")
        }
    }

    @Test
    fun testFindShipment() {
        val shipment = TrackingSimulator.findShipment("s10000")
        assert(shipment == null)

        TrackingSimulator.runSimulation()
        val shipment2 = TrackingSimulator.findShipment("s10000")
        assert(shipment2 != null)
        assert(shipment2?.status == "created")
    }

    @Test
    fun testAddShipment() {
        TrackingSimulator.addShipment(Shipment("s10000"))
        val shipment = TrackingSimulator.findShipment("s10000")
        assert(shipment != null)
        assert(shipment?.status == "created")
    }

    @Test
    fun testUpdate() {
        for (i in 0..9) {
            TrackingSimulator.runSimulation()
            val shipment = TrackingSimulator.findShipment("s1000$i")
            assert(shipment != null)
            assert(shipment?.status == "created")
        }
        for (i in 0..9) {
            TrackingSimulator.runSimulation()
            val shipment = TrackingSimulator.findShipment("s1000$i")
            assert(shipment != null)
            assert(shipment?.status == "shipped")
        }
    }
}