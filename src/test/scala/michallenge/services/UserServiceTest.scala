package michallenge.services

import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import michallenge.repositories.entity.{User, UserRequest}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import michallenge.repositories.UserRepository
import org.mockito.Mockito._
import org.scalatest.{AsyncWordSpec, Matchers}
import org.scalatestplus.mockito.MockitoSugar

import scala.concurrent.Future

class UserServiceTest
    extends AsyncWordSpec
    with Matchers
    with ScalatestRouteTest
    with MockitoSugar {

  private val userRepository = mock[UserRepository]
  private val userService = new UserService(userRepository)

  val dummyUser =
    User("123", "John", "Doe", "19", Seq("someEmail"), Seq("someNumber"))

  "UserService" when {
    "user get by id" should {
      "return user" in {
        when(userRepository.getUserById(dummyUser.id))
          .thenReturn(Future.successful(Some(dummyUser)))

        userService.getUserById(dummyUser.id).map { result =>
          result shouldBe dummyUser
        }
      }
    }

    "user create" should {
      "return true" in {
        val userRequest = UserRequest(
          dummyUser.firstName,
          dummyUser.lastName,
          dummyUser.age,
          dummyUser.number,
          dummyUser.email
        )
        when(userRepository.addUser(dummyUser))
          .thenReturn(Future.successful(true))

        userService.createUser(userRequest, "123").map { result =>
          result shouldBe true
        }
      }
    }

    "user update" should {
      "return true" in {
        val userRequest = UserRequest(
          dummyUser.firstName,
          dummyUser.lastName,
          dummyUser.age,
          dummyUser.number,
          dummyUser.email
        )
        when(userRepository.updateUser(dummyUser))
          .thenReturn(Future.successful(true))

        userService.updateUser(userRequest, dummyUser.id).map { result =>
          result shouldBe true
        }
      }
    }

    "user delete by id" should {
      "return true" in {
        when(userRepository.deleteUserById(dummyUser.id))
          .thenReturn(Future.successful(true))

        userService.deleteUserById(dummyUser.id).map { result =>
          result shouldBe true
        }
      }
    }
  }
}
