package controllers

import models.repo.SongRepo
import models.song.Song
import play.api.data.Form
import play.api.mvc.{Action, Controller}
import play.api.libs.json._
import play.api.mvc._
import play.api.data._

import scala.concurrent.Future
object Songs extends Controller {
  def get(id: Long) = Action.async {

    SongRepo.getById(id).map {
      case Some(song: Song) => Ok(Json.toJson(song))
      case _                => NoContent
    }
  }

  def list() = Action.async {
    SongRepo.list().map {
      case songs: Seq[Song] => Ok(Json.toJson(songs))
      case _                      => InternalServerError
    }
  }

  def post = Action.async { implicit request =>
    val song = Song(None, "Moby", "Extreme Ways")

    SongRepo.create(song) map {
      case Some(id: Long) => Created(Json.obj("created" -> id))
      case _              => InternalServerError(Json.obj("created" -> false))
    }

  }
}
