package michallenge.services

import java.util.UUID

import michallenge.repositories.entity.Profile.UserId
import michallenge.repositories.entity.{User, UserRequest}
import michallenge.repositories.{UserRepository, entity}

import scala.concurrent.{ExecutionContext, Future}

class UserService(userRepository: UserRepository)(
  implicit executionContext: ExecutionContext
) {

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

  def createUser(request: UserRequest): Future[Boolean] = {
    val newUser = entity.User(
      id = UUID.randomUUID().toString,
      firstName = request.firstName,
      lastName = request.lastName,
      age = request.age,
      number = request.number,
      email = request.email
    )
    userRepository.addUser(newUser)
  }

  def updateUser(id: UserId, request: UserRequest): Future[Boolean] = {
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
