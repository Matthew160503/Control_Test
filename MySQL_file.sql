CREATE DATABASE IF NOT EXISTS People_friends;

Use People_friends;

DROP TABLE IF EXISTS Animals;
CREATE TABLE Animals(
	id INT NOT NULL PRIMARY KEY,
    animal_type VARCHAR(45) NOT NULL,
    what_is_the_animal VARCHAR(45) NOT NULL,
    animal_name VARCHAR(45) NOT NULL,
    commands VARCHAR(100),
    birthday DATE NOT NULL
    );

INSERT Animals
VALUES
	(1,'Pet', 'Dog', 'Zack', 'даёт лапу, приседает', '2023-10-01'),
    (2,'Pet', 'Dog', 'Fill', 'приседает', '2019-03-27'),
    (3,'Pet', 'Cat', 'Palma', '', '2021-07-12'),
    (4,'Pet', 'Hamster', 'Nick', 'кусается', '2022-11-08'),
    (5,'Pet', 'Hamster', 'Lacky',' ', '2023-01-17'),
    (6,'Pack', 'Camel', 'Zill', '', '2021-09-25'),
    (7,'Pack', 'Horse', 'Montik', 'слушает, исполняет прыжки', '2018-04-07'),
    (8,'Pack', 'Donkey', 'Poly', '', '2020-10-18');
    
DROP VIEW IF EXISTS Pack_animals;
CREATE VIEW Pack_animals(id, what_is_the_animal, animal_name, commands, birthday)
	AS SELECT id, what_is_the_animal, animal_name, commands, birthday
		FROM Animals
	WHERE UPPER(animal_type) = UPPER('Pack');
    
DROP VIEW IF EXISTS Pets;
CREATE VIEW Pets(id, what_is_the_animal, animal_name, commands, birthday)
	AS SELECT id, what_is_the_animal, animal_name, commands, birthday
		FROM Animals
	WHERE UPPER(animal_type) = UPPER('Pet');

DROP VIEW IF EXISTS Dogs;
CREATE VIEW Dogs(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pets
	WHERE UPPER(what_is_the_animal) = UPPER('Dog');

DROP VIEW IF EXISTS Cats;
CREATE VIEW Cats(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pets
	WHERE UPPER(what_is_the_animal) = UPPER('Cat');

DROP VIEW IF EXISTS Hamsters;
CREATE VIEW Hamsters(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pets
	WHERE UPPER(what_is_the_animal) = UPPER('Hamster');

DROP VIEW IF EXISTS Camels;
CREATE VIEW Camels(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pack_animals
	WHERE UPPER(what_is_the_animal) = UPPER('Camel');

DROP VIEW IF EXISTS Horses;
CREATE VIEW Horses(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pack_animals
	WHERE UPPER(what_is_the_animal) = UPPER('Horse');

DROP VIEW IF EXISTS Donkeys;
CREATE VIEW Donkeys(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pack_animals
	WHERE UPPER(what_is_the_animal) = UPPER('Donkey');
    
SELECT * FROM Animals;

DELETE FROM Animals WHERE UPPER(what_is_the_animal) = UPPER('Camel');
DROP VIEW Camels;

DROP VIEW IF EXISTS Horses_And_Donkeys;
CREATE VIEW Horses_And_Donkeys(animal_name, commands, birthday)
	AS SELECT animal_name, commands, birthday
		FROM Pack_animals
	WHERE UPPER(what_is_the_animal) = UPPER('Horse') AND UPPER(what_is_the_animal) = UPPER('Donkey');
    
DROP VIEW IF EXISTS Young_animals;
CREATE VIEW Young_animals
	AS SELECT *, TIMESTAMPDIFF(DAY, birthday, curdate()) AS days 
		FROM Animals
	WHERE birthday BETWEEN '2020-01-01' AND '2022-12-31';
SELECT * FROM Young_animals;

SELECT * FROM Animals;