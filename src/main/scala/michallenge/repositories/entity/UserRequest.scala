package michallenge.repositories.entity

import michallenge.repositories.entity.Profile._
import play.api.libs.json.{Json, OFormat}

final case class UserRequest(firstName: Name,
                             lastName: Name,
                             age: Age,
                             number: Seq[PhoneNumber],
                             email: Seq[Email])

object UserRequest {
  implicit val createUserRequestFormat: OFormat[UserRequest] =
    Json.format[UserRequest]
}
