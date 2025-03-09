USE touristGuideDB;

-- alle byer
INSERT INTO cities (CityName) 
VALUES 
('Værløse'),
('København'),
('Amager'),
('Odense'),
('Århus'),
('Næstved'),
('Søborg'),
('Rønne'),
('Skagen'),
('Møn');

-- Tags
INSERT INTO tags (Tag) 
VALUES 
('Børnevenlig'),
('Familie'),
('Billig'),
('Teenager'),
('Voksne'),
('Mad & Drikke'),
('Fysisk aktivitet'),
('Afslapning'),
('Computer'),
('Historie'),
('Spændning');

-- Attractions
INSERT INTO attractions (AttractionName,AttractionDescription,CityID)
VALUES 
('Furesøbad', 'Populært badeområde ved Furesøen.', 1),
('Tivoli Gardens', 'En af verdens ældste forlystelsesparker.', 2),
('Amager Strandpark', 'Stor strandpark med laguner og udsigt til Øresund.', 3),
('H.C. Andersens Hus', 'Museum dedikeret til den berømte eventyrforfatter.', 4),
('Den Gamle By', 'Frilandsmuseum, der viser dansk byliv gennem tiderne.', 5),
('Gavnø Slot', 'Historisk slot med en smuk slotspark.', 6),
('Vangede Batteri', 'Historisk forsvarsværk fra 1800-tallet.', 7),
('Hammershus', 'Nordens største borgruin med fantastisk udsigt.', 8),
('Grenen', 'Danmarks nordligste punkt, hvor to have mødes.', 9),
('Møns Klint', 'Spektakulære kalkklinter med fantastisk udsigt.', 10),
('Den Lille Havfrue','Som fortalt om i HC. Andersens eventyr',2),
('Runde Tårn','Bygget af Kong Christian den 4.',2);

-- AttractionsTags
Insert INTO attraction_tags (AttractionsAttractionID, TagsID)
VALUES 
(1000, 1), (1000, 2), (1000, 4), (1000, 5), (1000, 11), -- Tivoli
(1001, 1), (1001, 2), (1001, 3), (1001, 7), (1001, 8), -- Furesøbad
(1002, 1), (1002, 2), (1002, 4), (1002, 5), (1002, 11), -- Tivoli Gardens
(1003, 1), (1003, 2), (1003, 3), (1003, 7), (1003, 8), -- Amager Strandpark
(1004, 2), (1004, 5), (1004, 10), -- H.C. Andersens Hus
(1005, 2), (1005, 5), (1005, 10), -- Den Gamle By
(1006, 2), (1006, 5), (1006, 10), (1006, 8), -- Gavnø Slot
(1007, 5), (1007, 10), -- Vangede Batteri
(1008, 5), (1008, 7), (1008, 10), -- Hammershus
(1009, 2), (1009, 7), (1009, 8), -- Grenen
(1010, 5), (1010, 7), (1010, 8), (1010, 10), -- Møns Klint
(1011, 1), (1011, 2), (1011, 10), -- Den Lille Havfrue
(1012, 5), (1012, 10); -- Runde Tårn



