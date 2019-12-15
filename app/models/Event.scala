package models

import play.api.libs.json.Reads._
import play.api.libs.json._

case class Event(text: String)

object Event {
  implicit val reads: Reads[Event] = (JsPath \ "text").read(minLength[String](1)).map(Event.apply)
}
