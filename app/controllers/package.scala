package object controllers {

  type Date = java.util.Date

  def DB = play.api.db.DB

  implicit def current = play.api.Play.current

  implicit def global = scala.concurrent.ExecutionContext.Implicits.global

  type DateTime = org.joda.time.DateTime

}
