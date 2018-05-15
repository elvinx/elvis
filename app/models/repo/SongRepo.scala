package models.repo

import anorm.SqlParser._
import models.song._
import anorm._

import scala.concurrent.Future

object SongRepo {

  val simple: RowParser[Song] =
    long("id") ~
      str("name") ~
      str("artist") map {
      case id ~ name ~ artist => Song(Some(id), name, artist)
    }

  def list(): Future[Seq[Song]] = Future {
    DB.withConnection{implicit connection =>

      SQL("""SELECT * from song""").as(simple *)
    }
  }

  def getById(id: Long) = Future {
    DB.withConnection { implicit connection =>
      SQL("""
        |SELECT
        | id,
        | name,
        | artist
        |FROM song
        |WHERE id = {id}
      """.stripMargin)
        .on('id -> id)
        .as(simple.singleOpt)
    }
  }

  def create(song: Song) = Future {
    DB.withConnection{ implicit  connection =>

      SQL(
        """
          |INSERT INTO song (
          |  name,
          |  artist
          |)
          |VALUES(
          |  {name},
          |  {artist}
          |)
        """.stripMargin).on(
        'name -> song.name,
        'artist -> song.artist
      ).executeInsert()
    }
  }

}
