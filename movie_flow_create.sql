CREATE SCHEMA IF NOT EXISTS movie_flow;
SET search_path = movie_flow;

CREATE TABLE IF NOT EXISTS movie (
    id SERIAL PRIMARY KEY,
    release_date TIMESTAMP NOT NULL,
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
    rate DOUBLE PRECISION NOT NULL,
    max_rate DOUBLE PRECISION NOT NULL,
    movie_id INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID
);

CREATE TABLE IF NOT EXISTS n2n_movie_to_actor (
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    PRIMARY KEY (movie_id, actor_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID,
    FOREIGN KEY (actor_id) REFERENCES actor(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID
);

CREATE TABLE IF NOT EXISTS n2n_movie_to_external_platform (
    movie_id INT NOT NULL,
    external_platform_id INT NOT NULL,
    movie_url TEXT NOT NULL,
    PRIMARY KEY (movie_id, external_platform_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID,
    FOREIGN KEY (external_platform_id) REFERENCES def_external_platform(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID
);

CREATE TABLE IF NOT EXISTS n2n_movie_to_genre (
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    PRIMARY KEY (movie_id, genre_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID,
    FOREIGN KEY (genre_id) REFERENCES def_genre(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID
);

CREATE TABLE IF NOT EXISTS n2n_movie_to_provider_company (
    movie_id INT NOT NULL,
    provider_company_id INT NOT NULL,
    PRIMARY KEY (movie_id, provider_company_id),
    FOREIGN KEY (movie_id) REFERENCES movie(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID,
    FOREIGN KEY (provider_company_id) REFERENCES def_provider_company(id) ON UPDATE NO ACTION ON DELETE NO ACTION NOT VALID
);
