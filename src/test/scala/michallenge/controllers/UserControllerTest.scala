package michallenge.controllers

import akka.http.scaladsl.model.ContentTypes.`application/json`
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import michallenge.repositories.entity.{User, UserRequest}
import michallenge.services.UserService
import org.scalamock.scalatest.MockFactory
import org.scalatest.{AsyncWordSpec, Matchers}
import org.scalamock.scalatest.MockFactory
import akka.http.scaladsl.model.{HttpEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.mockito.Mockito._
import org.scalatest.{AsyncWordSpec, BeforeAndAfterAll, Matchers}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

class UserControllerTest
    extends AsyncWordSpec
    with Matchers
    with ScalatestRouteTest
    with MockitoSugar {
  import PlayJsonSupport._

  private val userService = mock[UserService]
  private val userController = new UserController(userService)

  val dummyUser =
    User("123", "John", "Doe", "18", Seq("someEmail"), Seq("someNumber"))

  "UserController" when {

    "get users by id" should {
      "return OK" in {
        val userId = "123"
        when(userService.getUserById("123"))
          .thenReturn(Future.successful(dummyUser))

        Get("/users?id=" + userId) ~> userController.route ~> check {
          status shouldBe StatusCodes.OK
        }
      }
    }

    "create user" should {}

    "update user" should {}

    "delete user" should {}
  }
}
