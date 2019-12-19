package models

import play.api.libs.json._

case class Event1(field1: String)

object Event1 {
  implicit val reads: Reads[Event1] = Json.reads[Event1]
}

case class Event2(field2: String)

object Event2 {
  implicit val reads: Reads[Event2] = Json.reads[Event2]
}

case class Event3(field3: String)

object Event3 {
  implicit val reads: Reads[Event3] = Json.reads[Event3]
}
