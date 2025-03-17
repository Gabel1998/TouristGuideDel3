DROP DATABASE IF EXISTS touristGuideDB;
Create DATABASE touristGuideDB;
USE touristGuideDB;

CREATE TABLE cities (
CityName varchar(30) not null unique,
CityID int AUTO_INCREMENT PRIMARY KEY 
);

CREATE TABLE tags(
TagsID int AUTO_INCREMENT PRIMARY KEY,
Tag varchar(30) not null unique
);


CREATE TABLE attractions (
    AttractionName VARCHAR(30) not null unique,
    AttractionDescription VARCHAR(250),
	CityID int,
    AttractionID int AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (CityID) REFERENCES cities(CityID)
) AUTO_INCREMENT = 1000;


CREATE TABLE attraction_tags(
AttractionsAttractionID int,
TagsID int,
PRIMARY KEY(AttractionsAttractionID, TagsID),
FOREIGN KEY(AttractionsAttractionID) REFeRENCES attractions(AttractionID) ON DELETE CASCADE,
FOREIGN KEY(TagsID) REFERENCES tags(TagsID) ON DELETE CASCADE
);