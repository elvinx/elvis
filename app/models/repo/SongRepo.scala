package models.repo

import javax.inject.Inject

import anorm.SqlParser._
import models.song._
import anorm._
import play.api.db.DBApi
import play.api.libs.json._

import scala.concurrent.Future

class SongRepo @Inject()(dbapi: DBApi) {

  private val DB = dbapi.database("default")

  val simple = long("id") ~ str("name") ~ str("artist") ~ str("sections") map { case id ~ name ~ artist ~ sections => {
    val x = Json.fromJson[Seq[Section]](Json.parse(sections)).getOrElse(Seq.empty)
    Song(Some(id), name, artist, x)
  }
  }

  def list(): Future[Seq[Song]] = Future {
    DB.withConnection { implicit connection =>
      SQL("""SELECT * from song""").as(simple *)
    }
  }

  def getById(id: Long) = Future {
    DB.withConnection { implicit connection =>
      SQL(
        """
          |SELECT
          | id,
          | name,
          | artist,
          | sections
          |FROM song
          |WHERE id = {id}
        """.stripMargin).on('id -> id).as(simple.singleOpt)
    }
  }

  def create(song: Song) = Future {
    val sections_str: String = Json.stringify(Json.toJson(song.sections))

    DB.withConnection { implicit connection =>
      SQL(
        """
          |INSERT INTO song (
          |  name,
          |  artist,
          |  sections
          |)
          |VALUES(
          |  {name},
          |  {artist},
          |  {sections}
          |)
        """.stripMargin).on('name -> song.name, 'artist -> song.artist, 'sections -> sections_str).executeInsert()
    }
  }

}
