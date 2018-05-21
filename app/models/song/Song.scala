package models.song

import play.api.libs.json._

case class Song(id: Option[Long], name: String, artist: String, sections: Seq[Section])

object Song {
  implicit val jsFormat = Json.format[Song]
}
