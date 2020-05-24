package michallenge.services

import java.util.UUID

import michallenge.repositories.entity.Profile.UserId
import michallenge.repositories.entity.{User, UserRequest}
import michallenge.repositories.UserRepository

import scala.concurrent.{ExecutionContext, Future}

class UserService(userRepository: UserRepository)(
  implicit executionContext: ExecutionContext
) extends ProfileService {

  def getUserById(id: UserId): Future[User] = {
    userRepository
      .getUserById(id)
      .flatMap(
        user =>
          Future(user match {
            case Some(value) => value
            case None        => throw new Exception("user with this id doesn't exist")
          })
      )
  }

  def createUser(
    request: UserRequest,
    userId: String = UUID.randomUUID().toString
  ): Future[Boolean] = {
    val newUser = User(
      id = userId,
      firstName = request.firstName,
      lastName = request.lastName,
      age = request.age,
      number = request.number,
      email = request.email
    )
    userRepository.addUser(newUser)
  }

  def updateUser(request: UserRequest, id: UserId): Future[Boolean] = {
    userRepository
      .getUserById(id)
      .flatMap(
        userOpt =>
          userOpt.map { user =>
            userRepository.updateUser(
              user.copy(
                firstName = request.firstName,
                lastName = request.lastName,
                age = request.age,
                number = request.number,
                email = request.email
              )
            )
          } match {
            case Some(value) => value
            case None =>
              Future
                .failed(new RuntimeException("user with this id doesn't exist"))
        }
      )
  }

  def deleteUserById(id: UserId): Future[Boolean] = {
    userRepository.deleteUserById(id)
  }
}
