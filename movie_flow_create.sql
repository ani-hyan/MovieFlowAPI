CREATE SCHEMA IF NOT EXISTS movie_flow;
SET search_path = movie_flow;

CREATE TABLE IF NOT EXISTS movie (
                                     id SERIAL PRIMARY KEY,
                                     release_date DATE NOT NULL,
                                     description TEXT,
                                     duration_mins INTEGER,
                                     language TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS def_genre (
                                         id SERIAL PRIMARY KEY,
                                         name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS actor (
                                     id SERIAL PRIMARY KEY,
                                     first_name TEXT NOT NULL,
                                     last_name TEXT NOT NULL,
                                     nationality TEXT,
                                     gender TEXT,
                                     birth_date DATE
);

CREATE TABLE IF NOT EXISTS def_external_platform (
                                                     id SERIAL PRIMARY KEY,
                                                     name TEXT NOT NULL,
                                                     founded_date DATE
);

CREATE TABLE IF NOT EXISTS def_provider_company (
                                                    id SERIAL PRIMARY KEY,
                                                    name TEXT NOT NULL,
                                                    founded_date DATE
);

CREATE TABLE IF NOT EXISTS rating (
                                      id SERIAL PRIMARY KEY,
                                      rating_source TEXT NOT NULL,
                                      rate DOUBLE PRECISION NOT NULL,
                                      max_rate DOUBLE PRECISION NOT NULL,
                                      movie_id INT NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS n2n_movie_to_actor (
                                                  movie_id INT NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    actor_id INT NOT NULL REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS movie_to_external_platform_link (
                                                               movie_id INT NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    external_platform_id INT NOT NULL REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE,
    movie_url TEXT NOT NULL,
    PRIMARY KEY (movie_id, external_platform_id)
    );

CREATE TABLE IF NOT EXISTS n2n_movie_to_genre (
                                                  movie_id INT NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    genre_id INT NOT NULL REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE
    );

CREATE TABLE IF NOT EXISTS n2n_movie_to_provider_company (
                                                             movie_id INT NOT NULL REFERENCES movie(id) ON DELETE CASCADE ON UPDATE CASCADE,
    provider_company_id INT NOT NULL REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE
    );
