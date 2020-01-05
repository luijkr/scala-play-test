package controllers

import models._
import javax.inject._
import play.api.libs.json._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * HomeController
  *
  * @param cc         The base controller components dependencies that this controller relies on
  * @param executor   The environment this application runs in
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(implicit executor: ExecutionContext) extends AbstractController(cc) {

  def index(): Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Event1].fold(
      errors => Future (BadRequest(Json.obj("error" -> "Wrong body format."))),
      event => Future(Ok(Json.obj("status" -> "Seems good to me!")))
    )
  }

  def indexMultiple(): Action[JsValue] = Action.async(parse.json) { request =>
    Future(
      validateMultiple[Event1, Event2](request.body)
    )
  }

  private def validateMultiple[A, B](json: JsValue)(implicit rdsA: Reads[A], rdsB: Reads[B]): Result = {
    rdsA.reads(json) match {
      case JsSuccess(value, path) => Ok(Json.obj("status" -> "Event 1!"))
      case JsError(errors) => rdsB.reads(json) match {
        case JsSuccess(value, path) => Ok(Json.obj("status" -> "Event 2!"))
        case JsError(errors) => BadRequest(Json.obj("status" -> "Unknown Event!"))
      }
    }
  }
}
