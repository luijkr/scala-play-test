package controllers

import models.Event
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
    request.body.validate[Event].fold(
      errors => Future (BadRequest(Json.obj("error" -> "Wrong body format."))),
      event => Future(Ok(Json.obj("status" -> "All good!")))
    )
  }

  def status(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok("Application runs!")
  }
}
