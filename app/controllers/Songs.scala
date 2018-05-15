package controllers

import models.repo.SongRepo
import models.song.{Section, Song, Note}
import play.api.mvc.{Action, Controller}
import play.api.libs.json._

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
      case _                => InternalServerError
    }
  }

  def post = Action.async { implicit request =>
    val song = Song(None, "Moby", "Extreme Ways", Seq(Section("VERSE", Seq(Note("Oh baby", "G")))))

    SongRepo.create(song) map {
      case Some(id: Long) => Created(Json.obj("created" -> id))
      case _              => InternalServerError(Json.obj("created" -> false))
    }

  }
}
