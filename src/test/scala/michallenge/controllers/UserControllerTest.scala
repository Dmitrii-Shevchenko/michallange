package michallenge.controllers

import akka.http.scaladsl.model.ContentTypes.`application/json`
import michallenge.repositories.entity.{User, UserRequest}
import michallenge.services.UserService
import akka.http.scaladsl.model.{HttpEntity, StatusCodes}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.mockito.Mockito._
import org.scalatest.{AsyncWordSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

class UserControllerTest
    extends AsyncWordSpec
    with Matchers
    with ScalatestRouteTest
    with MockitoSugar {

  private val userService = mock[UserService]
  private val userController = new UserController(userService)

  val dummyUser =
    User("123", "John", "Doe", "19", Seq("someEmail"), Seq("someNumber"))

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

    "create user" should {
      "return OK" in {
        val userJson =
          s"""{"firstName":"John","lastName":"Doe","age":"19","number":["someEmail"],"email":["someNumber"]}"""
        val userRequest = UserRequest(
          dummyUser.firstName,
          dummyUser.lastName,
          dummyUser.age,
          dummyUser.number,
          dummyUser.email
        )
        val httpEntity = HttpEntity(`application/json`, userJson)
        when(userService.createUser(userRequest))
          .thenReturn(Future.successful(true))

        Post("/users", httpEntity) ~> userController.route ~> check {
          status shouldBe StatusCodes.OK
        }
      }
    }

    "update user" should {
      "return OK" in {
        val userJson =
          s"""{"firstName":"John","lastName":"Doe","age":"19","number":["someEmail"],"email":["someNumber"]}"""
        val userRequest = UserRequest(
          dummyUser.firstName,
          dummyUser.lastName,
          dummyUser.age,
          dummyUser.number,
          dummyUser.email
        )
        val httpEntity = HttpEntity(`application/json`, userJson)
        when(userService.updateUser(userRequest, dummyUser.id))
          .thenReturn(Future.successful(true))

        Put(s"/users?id=${dummyUser.id}", httpEntity) ~> userController.route ~> check {
          status shouldBe StatusCodes.NoContent
        }
      }
    }

    "delete user" should {
      "return OK" in {
        val userId = "123"
        when(userService.deleteUserById("123"))
          .thenReturn(Future.successful(true))

        Delete("/users?id=" + userId) ~> userController.route ~> check {
          status shouldBe StatusCodes.OK
        }
      }
    }
  }
}
