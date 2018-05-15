package models.song

import play.api.libs.json.Json

case class Chord(
    id: Long,
    name: String
)

object Chord {
  implicit val jsFormat = Json.format[Chord]
}
