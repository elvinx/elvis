package models.song

case class Section (
  id: Long,
  name: SectionType,
  notes: Seq[Note]
)
