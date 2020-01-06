package models

import play.api.libs.json._

case class Request0(name: String, message: String)

object Request0 {
  implicit val reads: Reads[Request0] = Json.reads[Request0]
}

case class Request1(company: String, job_title: String)

object Request1 {
  implicit val reads: Reads[Request1] = Json.reads[Request1]
}
