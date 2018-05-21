package models

package object repo {

  type Date = java.util.Date

  def Logger = play.api.Logger

  implicit def current = play.api.Play.current

  implicit def global = scala.concurrent.ExecutionContext.Implicits.global

}
