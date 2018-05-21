package controllers

import models.repo.SongRepo
import models.song.{Note, Section, Song}
import play.api.mvc._
import play.api.libs.json._
import javax.inject.{Inject, Singleton}

@Singleton class Songs @Inject()(songRepo: SongRepo, cc: ControllerComponents) extends AbstractController(cc) {

  def get(id: Long) = Action.async {
    songRepo.getById(id).map { case Some(song: Song) => Ok(Json.toJson(song))
    case _ => NoContent
    }
  }

  def list() = Action.async {
    songRepo.list().map { case songs: Seq[Song] => Ok(Json.toJson(songs))
    case _ => InternalServerError
    }
  }

  def post = Action.async { implicit request =>
    val song = Song(None, "Moby", "Extreme Ways", Seq(Section("VERSE", Seq(Note("Oh baby", "G")))))

    songRepo.create(song) map { case Some(id: Long) => Created(Json.obj("created" -> id))
    case _ => InternalServerError(Json.obj("created" -> false))
    }

  }
}
