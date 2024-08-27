package net.nmoncho.helenus.examples.hotels.repositories

import net.nmoncho.helenus.examples.hotels.HotelsTestData
import net.nmoncho.helenus.examples.hotels.models.{ Amenity, Hotel }
import akka.actor.ActorSystem
import akka.stream.scaladsl.Sink
import akka.testkit.TestKit
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{ Seconds, Span }
import org.scalatest.wordspec.AnyWordSpecLike

import java.time.LocalDate
import scala.concurrent.Await
import scala.concurrent.duration.DurationInt

class HotelRepositorySpec
    extends TestKit(ActorSystem())
    with AnyWordSpecLike
    with Matchers
    with CassandraSpec
    with ScalaFutures:

    import system.dispatcher

    "HotelRepository" should {
        "query hotels by POI" in {
            val repository = new HotelRepository()
            val queryStream = repository
                  .findByPOI(
                    HotelsTestData.PointOfInterests.rotterdamEuromast.name
                  )
                  .runWith(Sink.seq[Hotel])
            
            whenReady(queryStream)(hotels => hotels should not be empty)
        }

        "query hotel by id" in {
            val repository = new HotelRepository()

            whenReady(repository.findById(HotelsTestData.Hotels.h1.id)) { hotel =>
                hotel shouldBe Some(HotelsTestData.Hotels.h1)
            }
        }

        "query POI by hotel" in {
            val repository = new HotelRepository()
            val queryStream = repository
                  .findPOIsByHotel(HotelsTestData.Hotels.h1.id)
                  .map(_.name)
                  .runWith(Sink.seq[String])
            
            whenReady(queryStream)(_.toSet shouldBe HotelsTestData.Hotels.h1.pois)
        }

        "query available room" in {
            val repository = new HotelRepository()
            val queryStream = repository
                  .availableRooms(HotelsTestData.Hotels.h1.id, LocalDate.parse("2023-01-01"))
                  .runWith(Sink.seq[HotelRepository.RoomNumber])
            
            whenReady(queryStream) { january1st =>
                january1st should not be empty
            }
        }

        "query room amenities" in {
            val repository = new HotelRepository()
            val queryStream = repository
                  .roomAmenities(HotelsTestData.Hotels.h1.id, 1.toShort)
                  .runWith(Sink.seq[Amenity])

            whenReady(queryStream) { result =>
                result should not be empty
            }
        }
    }

    override def beforeAll(): Unit =
        super.beforeAll()
        executeFile("hotels.cql")

        Await.ready(
          HotelsTestData.insertTestData(),
          60.seconds
        )
    end beforeAll

    override implicit def patienceConfig: PatienceConfig = PatienceConfig(Span(6, Seconds))
end HotelRepositorySpec
