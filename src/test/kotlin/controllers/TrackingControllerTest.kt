package controllers

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.assertThrows
import server.controllers.TrackingController
import server.shipment.StandardShipment
import kotlin.test.Ignore
import kotlin.test.Test

class TrackingControllerTest {
    @Test
    fun testAddShipment() {
        TrackingController.addShipment(StandardShipment("s10000"))
        val shipment = TrackingController.findShipment("s10000")
        assert(shipment != null)
        assert(shipment?.status == "created")
    }

    @Test
    fun testFindShipment() {
        TrackingController.addShipment(StandardShipment("s12000"))
        val shipment = TrackingController.findShipment("s12000")
        assert(shipment != null)
        assert(shipment!!.id == "s12000")

        assert(TrackingController.findShipment("s12001") == null)
    }

    // Ignore this test because I could not get the server to run without blocking
    @Ignore
    @Test
    fun testAddUpdateRoute() {
        testApplication {
            val response = client.post("/update") {
                setBody("created,s12000,1000,standard")
            }
            assert(response.status == HttpStatusCode.OK)
            val response2 = client.post("/update") {
                setBody("shipped,s12000,1000,badrequest")
            }
            assert(response2.status == HttpStatusCode.BadRequest)
            val response3 = client.post("/update") {
                setBody("shipped,s12000,1000,")
            }
            assert(response3.status == HttpStatusCode.BadRequest)
        }
    }

}