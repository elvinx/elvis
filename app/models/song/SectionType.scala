package models.song

sealed trait SectionType {
  val value: String
}

case object Intro extends SectionType {
  val value = "INTRO"
}

case object Verse extends SectionType {
  val value = "VERSE"
}

case object Chorus extends SectionType {
  val value = "CHORUS"
}

case object Outro extends SectionType {
  val value = "OUTRO"
}