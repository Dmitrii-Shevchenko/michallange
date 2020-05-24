package michallenge.repositories.entity

import michallenge.repositories.entity.Profile._
import play.api.libs.json.{Json, Writes}

final case class User(id: UserId,
                      firstName: Name,
                      lastName: Name,
                      age: Age,
                      number: Seq[PhoneNumber],
                      email: Seq[Email])
    extends Profile
object User {
  implicit val locationWrites = new Writes[User] {
    def writes(user: User) = Json.obj(
      "id" -> user.id,
      "firstName" -> user.firstName,
      "lastName" -> user.lastName,
      "age" -> user.age,
      "number" -> user.number,
      "email" -> user.email
    )
  }
}
