package michallenge.portal

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import michallenge.controllers.UserController
import michallenge.repositories.UserRepository
import michallenge.services.UserService
import com.redis._

import scala.concurrent.ExecutionContextExecutor

object MainApp extends App {
  println("Hello world!")
  implicit val system: ActorSystem = ActorSystem("MIChallange")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  implicit val log = Logging(system, "main")

  val redisClient = new RedisClient("localhost", 6379)

  val port = 8080

  val route =
    new UserController(new UserService(new UserRepository(redisClient))).route

  val bindingFuture =
    Http().bindAndHandle(route, "localhost", port)

  log.info(s"Server started at the port $port")
}
