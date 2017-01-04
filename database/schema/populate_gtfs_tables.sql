-- DirectionType
DELETE FROM  T_DirectionType;

INSERT INTO T_DirectionType(id,short_name,long_name,description)
VALUES(0,'OT','Outbound travel','Travel in one direction');

INSERT INTO T_DirectionType(id,short_name,long_name,description)
VALUES(1,'IT','Inbound travel','Travel in the opposite direction');

-- TransportType
DELETE FROM T_TransportType;

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(0,'Tram','Tram, Streetcar, Light rail','Any light rail or street level system within a metropolitan area');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(1,'Subway','Subway, Metro','Any underground rail system within a metropolitan area');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(2,'Rail','Rail','Used for intercity or long-distance travel');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(3,'Bus','Bus','Used for short- and long-distance bus routes');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(4,'Ferry','Ferry','Used for short- and long-distance boat service');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(5,'Cable car','Cable car','Used for street-level cable cars where the cable runs beneath the car.');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(6,'Gondola','Gondola, Suspended cable car','Typically used for aerial cable cars where the car is suspended from the cable');

INSERT INTO T_TransportType(id,short_name,long_name,description)
VALUES(7,'Funicular','Funicular','Any rail system designed for steep inclines');



