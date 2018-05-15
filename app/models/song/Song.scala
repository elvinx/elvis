package models.song

import play.api.libs.json._
case class Song(
    id: Option[Long],
    name: String,
    artist: String
    //sections: Option[Seq[Section]] = None
)
object Song {
  implicit val jsFormat = Json.format[Song]
}
