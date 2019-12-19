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
      event => Future(Ok(Json.obj("status" -> "All good!")))
    )
  }

  def validate[A, B, C](json: JsValue)(implicit rdsA: Reads[A], rdsB: Reads[B], rdsC: Reads[C]): Result = {
    val validate = rdsA.reads(json)
    validate match {
      case JsSuccess(value, path) => Ok(Json.obj("status" -> "Class A!"))
      case JsError(errors) => rdsB.reads(json) match {
        case JsSuccess(value, path) => Ok(Json.obj("status" -> "Class B!"))
        case JsError(errors) => rdsC.reads(json) match {
          case JsSuccess(value, path) => Ok(Json.obj("status" -> "Class C!"))
          case JsError(errors) => BadRequest(Json.obj("status" -> "None!"))
        }
      }
    }
  }

  def indexMultiple(): Action[JsValue] = Action.async(parse.json) { request =>
    val body = request.body
    Future( validate[Event1, Event2, Event3](body) )
  }

  def status(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Application runs!")
  }
}
