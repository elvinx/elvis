package models.song

import play.api.libs.json.Json

case class Section(
    name: String,
    notes: Seq[Note]
)
object Section {
  implicit val jsFormat = Json.format[Section]
}
