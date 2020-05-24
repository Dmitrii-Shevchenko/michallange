package michallenge.services

import michallenge.repositories.entity.Profile.UserId
import michallenge.repositories.entity.{User, UserRequest}

import scala.concurrent.Future

trait ProfileService {
  def getUserById(id: UserId): Future[User]
  def createUser(request: UserRequest, userId: UserId): Future[Boolean]
  def updateUser(request: UserRequest, userId: UserId): Future[Boolean]
  def deleteUserById(id: UserId): Future[Boolean]
}
