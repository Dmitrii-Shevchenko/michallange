package michallenge.repositories

import michallenge.repositories.entity.Profile.UserId
import michallenge.repositories.entity.User
import michallenge.utils.Serializer
import com.redis.RedisClient

import scala.concurrent.{ExecutionContext, Future}
import com.redis.serialization.Parse.Implicits._

class UserRepository(redisClient: RedisClient)(
  implicit executionContext: ExecutionContext
) {
  def addUser(user: User): Future[Boolean] = {
    Future(redisClient.set(user.id, Serializer.serialize(user)))
  }
  def updateUser(user: User): Future[Boolean] =
    Future(redisClient.set(user.id, Serializer.serialize(user)))

  def getUserById(id: UserId): Future[Option[User]] = {
    Future(
      redisClient
        .get[Array[Byte]](id)
        .flatMap(user => Option(Serializer.deserialize(user)))
    )
  }

  def deleteUserById(id: UserId): Future[Boolean] = {
    Future(redisClient.del(id) match {
      case Some(_) => true
      case None    => false
    })
  }

}
