/*
Order of creation 
DirectionType, TransportType = referenced by other tables
City, Agency
CityAgency
Route
Trips
Stops
StopTimes
CalendarDates
Calendar
Currency
FareAttributes
Transfers
FeedInfo

Drop order
FeedInfo
Transfers
FareAttributes
Currency
Calendar
CalendarDates
StopTimes
Stops
Trips
Route
CityAgency
City, Agency
DirectionType, TransportType
*/


-- This table contains values from the GTFS standard

CREATE TABLE T_DirectionType(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	short_name 	text 		NOT 	NULL,
	long_name 	text 		NOT 	NULL,
	description	text 		NOT	NULL
);

CREATE UNIQUE INDEX I_DirectionType ON T_DirectionType (id,short_name);


-- This table contains values from the GTFS standard

CREATE TABLE T_TransportType(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	short_name 	text 		NOT 	NULL,
	long_name 	text 		NOT 	NULL,
	description	text 		NOT	NULL
);

CREATE UNIQUE INDEX I_TransportType ON T_TransportType (id,short_name);


CREATE SEQUENCE S_City INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_City(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	name		text 		NOT 	NULL
);

CREATE UNIQUE INDEX I_City ON T_City (id, name);
ALTER TABLE T_City ALTER COLUMN id SET DEFAULT nextval('S_City') ;


CREATE SEQUENCE S_Agency INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Agency(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	name		text 		NOT 	NULL,	-- GTFS:agency_name
	url		text 		NOT 	NULL,	-- GTFS:agency_url
	timezone	text 		NOT 	NULL,	-- GTFS:timezone
	lang		text			NULL,	-- GTFS:lang
	phone		text			NULL,	-- GTFS:phone
	fare_url	text			NULL,	-- GTFS:fare_url
	email		text			NULL,	-- GTFS:email
	gtfs_agency_id 	text 			NULL	-- GTFS:agency_id
);

CREATE UNIQUE INDEX I_Agency ON T_Agency (id, name);
ALTER TABLE T_Agency ALTER COLUMN id SET DEFAULT nextval('S_Agency') ;

-- Link table between City and Agency - will help in the future
CREATE TABLE T_CityAgency(
	id_city		integer 	REFERENCES T_City(id),
	id_agency	integer 	REFERENCES T_Agency(id)
);

CREATE UNIQUE INDEX I_CityAgency ON T_CityAgency (id_city, id_agency);


CREATE SEQUENCE S_Routes INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Routes(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	short_name 	text 		NOT 	NULL,
	long_name 	text 		NOT 	NULL,
	description	text 			NULL,
	type		integer 	REFERENCES T_TransportType(id),
	url		text 			NULL,
	color		text 			NULL,
	text_color 	text 			NULL,
	gtfs_route_id	text		NOT	NULL,
	gtfs_agency_id	text			NULL
);

CREATE UNIQUE INDEX I_Routes ON T_Routes (id, /*id_agency,*/short_name);
ALTER TABLE T_Routes ALTER COLUMN id SET DEFAULT nextval('S_Routes') ;


CREATE SEQUENCE S_Trips INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Trips(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	headsign 	text 		 	NULL,
	short_name	text 			NULL,
	id_direction	integer			REFERENCES T_DirectionType(id),
	gtfs_block_id	text			NULL,
	gtfs_shape_id	text 		NULL,
wheelchair_accessible	integer			NULL,
bikes_allowed		integer			NULL,
	gtfs_route_id	text		NOT	NULL, -- GTFS: route_id
	gtfs_service_id	text		NOT	NULL, -- GTFS: sevice_id
	gtfs_trip_id	text		NOT	NULL -- GTFS: trip_id
);

CREATE UNIQUE INDEX I_Trips ON T_Trips (id, short_name);
ALTER TABLE T_Trips ALTER COLUMN id SET DEFAULT nextval('S_Trips') ;



CREATE SEQUENCE S_Stops INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Stops(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	stop_id		text		NOT 	NULL,
	code	text			NULL,
	name	text		NOT	NULL,
	description	text			NULL,
	lat	text		NOT	NULL,
	lon	text		NOT	NULL,
	zone_id		text			NULL,
	url	text			NULL,
	location_type	text			NULL,
	parent_station	text			NULL,
	timezone	text			NULL,
	wheelchair_boarding	text		NULL
);



CREATE UNIQUE INDEX I_Stops ON T_Stops (id, name, code);
ALTER TABLE T_Stops ALTER COLUMN id SET DEFAULT nextval('S_Stops') ;



CREATE SEQUENCE S_StopTimes INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_StopTimes(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	id_trip		integer		REFERENCES T_Trips(id),
	arrival_time	time		NOT	NULL,
	departure_time	time 		NOT	NULL,
	stop_id		integer		REFERENCES T_Stops(id),
	stop_sequence	integer		NOT	NULL,
	stop_headsign	text			NULL,
	pickup_type	integer		DEFAULT 0,
	drop_off_type	integer		DEFAULT 0,
	shape_dist_traveled	text		NULL,
	timepoint	integer			NULL,
	CONSTRAINT C_ST_PickupType CHECK (pickup_type>=0 and pickup_type<=3),--only 0, 1, 2 and 3 are allowed
	CONSTRAINT C_ST_DropOffType CHECK (drop_off_type>=0 and drop_off_type<=3),--only 0, 1, 2 and 3 are allowed
	CONSTRAINT C_ST_Timepoint CHECK (timepoint>=0 and timepoint<=1)	--only 0, 1 and NULL are allowed 
);

CREATE UNIQUE INDEX I_StopTimesSequence ON T_StopTimes (id_trip, stop_sequence);
ALTER TABLE T_StopTimes ALTER COLUMN id SET DEFAULT nextval('S_StopTimes') ;


CREATE SEQUENCE S_CalendarDates INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_CalendarDates(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	id_trip		integer		REFERENCES T_Trips(id),
	calendar_date	date		NOT	NULL,
	exception_type	integer 		NOT	NULL,
	CONSTRAINT C_CD_ExceptionType CHECK (exception_type in (1,2))--only 1, 2 are allowed
);

CREATE UNIQUE INDEX I_CalendarDates ON T_CalendarDates (id_trip, calendar_date);
ALTER TABLE T_CalendarDates ALTER COLUMN id SET DEFAULT nextval('S_CalendarDates') ;



CREATE SEQUENCE S_Calendar INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Calendar(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	id_trip		integer		REFERENCES T_Trips(id),
	monday		boolean		NOT	NULL,
	tuesday		boolean		NOT	NULL,
	wednesday	boolean		NOT	NULL,
	thursday	boolean		NOT	NULL,
	friday		boolean		NOT	NULL,
	saturday	boolean		NOT	NULL,
	sunday		boolean		NOT	NULL,
	start_date	date 		NOT	NULL,
	end_date	date 		NOT	NULL
);

ALTER TABLE T_Calendar ALTER COLUMN id SET DEFAULT nextval('S_Calendar') ;



CREATE SEQUENCE S_Currency INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Currency(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	code		CHARACTER(3) 	NOT NULL,
	currency_name	CHARACTER VARYING(255)		NOT	NULL
);

CREATE UNIQUE INDEX I_Currency ON T_Currency (code);
ALTER TABLE T_Currency ALTER COLUMN id SET DEFAULT nextval('S_Currency') ;



CREATE SEQUENCE S_FareAttributes INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_FareAttributes(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	price		numeric 	NOT NULL,
	id_currency	integer		REFERENCES T_Currency(id),
	payment_method	integer		NOT 	NULL,
	transfers	integer			NULL,
	transfer_duration	integer		NULL,
	CONSTRAINT C_FA_PaymentMethod CHECK (payment_method in (0,1)),--0: Fare is paid on board. 1: Fare must be paid before boarding.
	CONSTRAINT C_FA_Transfers CHECK (transfers in (0,1,2))--0: No transfers permitted on this fare.1: Passenger may transfer once.2: Passenger may transfer twice.(empty): If this field is empty, unlimited transfers are permitted.
);

ALTER TABLE T_FareAttributes ALTER COLUMN id SET DEFAULT nextval('S_FareAttributes') ;



CREATE SEQUENCE S_Transfers INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_Transfers(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	from_stop_id	integer 	REFERENCES T_Stops(id),
	to_stop_id	integer		REFERENCES T_Stops(id),
	transfer_type	integer		DEFAULT 0,
	min_transfer_time	integer			NULL,
	CONSTRAINT C_T_TransferType CHECK (transfer_type between 0 and 3)	--0 or (empty): This is a recommended transfer point between two routes.
										--1: This is a timed transfer point between two routes. The departing vehicle is expected to wait for the arriving one, with sufficient time for a passenger to transfer between routes.
										--2: This transfer requires a minimum amount of time between arrival and departure to ensure a connection. The time required to transfer is specified by min_transfer_time.
										--3: Transfers are not possible between routes at this location.
);

ALTER TABLE T_Transfers ALTER COLUMN id SET DEFAULT nextval('S_Transfers') ;



CREATE SEQUENCE S_FeedInfo INCREMENT  BY 1 
     START WITH  1 ;

CREATE TABLE T_FeedInfo(
	id 		integer 	PRIMARY KEY, 	-- internal to the database
	feed_publisher_name	text 	NOT	NULL,
	feed_publisher_url	text	NOT	NULL,
	feed_lang	character varying(128)		NOT NULL,
	feed_start_date	date			NULL,
	feed_end_date	date			NULL,
	feed_version	character varying(32)	NULL
);

ALTER TABLE T_FeedInfo ALTER COLUMN id SET DEFAULT nextval('S_FeedInfo') ;
