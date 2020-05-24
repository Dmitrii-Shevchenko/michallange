package michallenge.repositories

import michallenge.repositories.entity.Profile.UserId
import michallenge.repositories.entity.User

import scala.concurrent.Future

trait ProfileRepository {
  def addUser(user: User): Future[Boolean]
  def updateUser(user: User): Future[Boolean]
  def getUserById(id: UserId): Future[Option[User]]
  def deleteUserById(id: UserId): Future[Boolean]
}
