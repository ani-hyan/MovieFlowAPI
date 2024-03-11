SET search_path = movie_flow;

INSERT INTO movie_flow.movie (release_date, description, duration_mins, language)
VALUES 
    ('2022-01-15', 'Inception', 148, 'English'),
    ('2019-05-02', 'Parasite', 132, 'Korean'),
    ('2020-12-18', 'Soul', 100, 'English'),
    ('2017-12-15', 'The Shape of Water', 123, 'English'),
    ('2015-04-10', 'Mad Max: Fury Road', 120, 'English'),
    ('2018-09-21', 'A Star Is Born', 136, 'English'),
    ('2019-11-22', 'Knives Out', 130, 'English'),
    ('2016-03-04', 'Zootopia', 108, 'English'),
    ('2014-11-07', 'Interstellar', 169, 'English'),
    ('2013-06-21', 'The Heat', 117, 'English');

INSERT INTO def_genre (name)
VALUES 
    ('Action'),
    ('Drama'),
    ('Comedy'),
    ('Science Fiction'),
    ('Horror'),
    ('Romance'),
    ('Thriller'),
    ('Animation'),
    ('Adventure'),
    ('Documentary');

INSERT INTO actor (first_name, last_name, nationality, gender, birth_date)
VALUES
    ('Leonardo', 'DiCaprio', 'American', 'Male', '1974-11-11'),
    ('Song', 'Kang-ho', 'South Korean', 'Male', '1967-01-17'),
    ('Jamie', 'Foxx', 'American', 'Male', '1967-12-13'),
    ('Sally', 'Hawkins', 'British', 'Female', '1976-04-27'),
    ('Charlize', 'Theron', 'South African', 'Female', '1975-08-07'),
    ('Lady', 'Gaga', 'American', 'Female', '1986-03-28'),
    ('Daniel', 'Craig', 'British', 'Male', '1968-03-02'),
    ('Ginnifer', 'Goodwin', 'American', 'Female', '1978-05-22'),
    ('Matthew', 'McConaughey', 'American', 'Male', '1969-11-04'),
    ('Sandra', 'Bullock', 'American', 'Female', '1964-07-26'),
	('Tina', 'Fey', 'American', 'Female', '1970-05-18'),
    ('Graham', 'Norton', 'Irish', 'Male', '1963-04-04'),
	('Charlize', 'Theron', 'South African', 'Female', '1975-08-07'),
	('Lady', 'Gaga', 'American', 'Female', '1986-03-28'),
    ('Bradley', 'Cooper', 'American', 'Male', '1975-01-05'),
	('Ana', 'de Armas', 'Cuban', 'Female', '1988-04-30'),
    ('Chris', 'Evans', 'American', 'Male', '1981-06-13'),
	('Ginnifer', 'Goodwin', 'American', 'Female', '1978-05-22'),
    ('Jason', 'Bateman', 'American', 'Male', '1969-01-14'),
	('Matthew', 'McConaughey', 'American', 'Male', '1969-11-04'),
    ('Anne', 'Hathaway', 'American', 'Female', '1982-11-12'),
	('Sandra', 'Bullock', 'American', 'Female', '1964-07-26'),
    ('Melissa', 'McCarthy', 'American', 'Female', '1970-08-26');
	
INSERT INTO def_external_platform (name, founded_date)
VALUES 
    ('Netflix', '1997-08-29'),
    ('Hulu', '2007-10-29'),
    ('Amazon Prime Video', '2006-09-07'),
    ('Disney+', '2019-11-12'),
    ('HBO Max', '2020-05-27');

INSERT INTO def_provider_company (name, founded_date)
VALUES 
    ('DNEG', '1988-01-01'),
    ('Barunson E&A', '1996-12-27'),
    ('Pixar Animation Studios', '1986-02-03'),
    ('Searchlight Pictures', '1994-01-01'),
    ('Double Dare You', '1986-10-06'),
    ('TSG Entertainment', '2012-01-01'),
    ('Village Roadshow Pictures', '1954-01-01'),
    ('Warner Bros', '1923-04-04'),
    ('Live Nation Productions', '1995-01-25'),
    ('Lions Gate Films', '1997-07-10'),
    ('Walt Disney Animation Studios', '1923-10-16'),
    ('Paramount Pictures', '1912-05-08'),
    ('Legendary Entertainment', '2000-01-01');

INSERT INTO rating (rating_source, rate, max_rate, movie_id)
VALUES 
    ('IMDb', 8.7, 10, 1),  -- Inception
    ('Rotten Tomatoes', 9.0, 10, 2),  -- Parasite
    ('Metacritic', 8.5, 10, 3),  -- Soul
    ('IMDb', 7.9, 10, 4),  -- The Shape of Water
    ('Rotten Tomatoes', 8.8, 10, 5),  -- Mad Max: Fury Road
    ('IMDb', 8.2, 10, 6),  -- A Star Is Born
    ('Rotten Tomatoes', 9.5, 10, 7),  -- Knives Out
    ('Metacritic', 8.4, 10, 8),  -- Zootopia
    ('IMDb', 8.6, 10, 9),  -- Interstellar
    ('Rotten Tomatoes', 7.7, 10, 10);  -- The Heat

INSERT INTO n2n_movie_to_actor (movie_id, actor_id)
VALUES 
    (1, 1),  -- Inception - Leonardo DiCaprio
    (2, 2),  -- Parasite - Song Kang-ho
    (3, 3),  -- Soul - Jamie Foxx
    (4, 4),  -- The Shape of Water - Sally Hawkins
    (5, 5),  -- Mad Max: Fury Road - Charlize Theron
    (6, 6),  -- A Star Is Born - Lady Gaga
    (7, 7),  -- Knives Out - Daniel Craig
    (8, 8),  -- Zootopia - Ginnifer Goodwin
    (9, 9),  -- Interstellar - Matthew McConaughey
    (10, 10),  -- The Heat - Sandra Bullock
    (7, 11),  -- Knives Out - Tina Fey
    (7, 12),  -- Knives Out - Graham Norton
    (6, 13),  -- A Star Is Born - Bradley Cooper
    (7, 14),  -- Knives Out - Ana de Armas
    (8, 15),  -- Zootopia - Chris Evans
    (9, 16),  -- Interstellar - Ginnifer Goodwin
    (10, 17),  -- The Heat - Jason Bateman
    (9, 18),  -- Interstellar - Matthew McConaughey
    (10, 19),  -- The Heat - Anne Hathaway
    (10, 20);  -- The Heat - Melissa McCarthy

INSERT INTO n2n_movie_to_external_platform (movie_id, external_platform_id, movie_url)
VALUES 
    (1, 1, 'https://netflix.com/inception'),  -- Inception on Netflix
    (2, 2, 'https://hulu.com/parasite'),  -- Parasite on Hulu
    (3, 3, 'https://amazon.com/soul'),  -- Soul on Amazon Prime Video
    (4, 4, 'https://disneyplus.com/shape-of-water'),  -- The Shape of Water on Disney+
    (5, 5, 'https://hbomax.com/mad-max-fury-road');
	
INSERT INTO n2n_movie_to_genre (movie_id, genre_id)
VALUES 
    (1, 1),  -- Inception - Action
	(1, 2),	 -- Inception - Drama
    (2, 2),  -- Parasite - Drama
    (3, 6),  -- Soul - Romance
    (4, 2),  -- The Shape of Water - Drama
    (5, 3),  -- Mad Max: Fury Road - Comedy
    (6, 6),  -- A Star Is Born - Romance
    (7, 7),  -- Knives Out - Thriller
    (8, 8),  -- Zootopia - Animation
    (9, 4),  -- Interstellar - Science Fiction
    (10, 3);  -- The Heat - Comedy
	
INSERT INTO n2n_movie_to_provider_company(movie_id, provider_company_id)
VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(4, 5),
	(4, 6),
	(5, 7),
	(6, 8),
	(6, 9),
	(7, 10),
	(8, 11),
	(9, 12),
	(9, 8),
	(9, 13),
	(10, 8);
