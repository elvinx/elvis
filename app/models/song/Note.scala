package models.song

import play.api.libs.json.Json

case class Note(
    text: String,
    chord: String
)
object Note {
  implicit val jsFormat = Json.format[Note]
}
