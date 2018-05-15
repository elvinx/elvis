
# --- !Ups

CREATE TABLE "song" ( `id` INTEGER PRIMARY KEY AUTOINCREMENT, `artist` TEXT, `name` TEXT, `sections` TEXT )

# --- !Downs

DROP TABLE song;