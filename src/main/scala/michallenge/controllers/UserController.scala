package michallenge.controllers

import michallenge.services.UserService
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Route
import cats.data.Validated.{Invalid, Valid}
import michallenge.repositories.entity.UserRequest
import michallenge.repositories.validator.{FormValidatorNec, FormValidatorNel}
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport._
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class UserController(userService: UserService)(
  implicit executionContext: ExecutionContext
) {
  val route: Route = path("users") {
    get {
      parameter("id".as[String]) { id =>
        onComplete(userService.getUserById(id)) {
          case Success(user) => complete(StatusCodes.OK -> Json.toJson(user))
          case Failure(exc) =>
            complete(StatusCodes.InternalServerError, exc.getMessage)
        }
      }
    } ~
      post {
        entity(as[UserRequest]) { request =>
          FormValidatorNel.validateForm(request) match {
            case Valid(validatedRequest) =>
              onComplete(userService.createUser(validatedRequest)) {
                case Success(_) => complete(StatusCodes.OK)
                case _          => complete(StatusCodes.BadRequest)
              }
            case Invalid(errors) =>
              complete(StatusCodes.BadRequest -> errors.toList.map(_.toMap))
          }
        }
      } ~
      put {
        parameter("id".as[String]) { id =>
          entity(as[UserRequest]) { request =>
            FormValidatorNel.validateForm(request) match {
              case Valid(validatedRequest) =>
                onComplete(userService.updateUser(id, validatedRequest)) {
                  case Success(_) => complete(StatusCodes.NoContent)
                  case _          => complete(StatusCodes.BadRequest)
                }
              case Invalid(errors) =>
                complete(StatusCodes.BadRequest -> errors.toList.map(_.toMap))
            }
          }
        }
      } ~
      delete {
        parameter("id".as[String]) { id =>
          onComplete(userService.deleteUserById(id)) {
            case Success(_) => complete(StatusCodes.OK)
            case Failure(exc) =>
              complete(StatusCodes.InternalServerError, exc.getMessage)
          }
        }
      }
  }
}
