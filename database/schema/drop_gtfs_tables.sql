/*
 Order of creation 
DirectionType, TransportType
City, Agency
CityAgency
Route
Trip
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
Trip
Route
CityAgency
City, Agency
DirectionType, TransportType
*/

-- The order of the drop is : first index, second table, third sequence
DROP TABLE IF EXISTS T_FeedInfo;
DROP SEQUENCE S_FeedInfo;



DROP TABLE IF EXISTS T_Transfers;
DROP SEQUENCE S_Transfers;



DROP TABLE IF EXISTS T_FareAttributes;
DROP SEQUENCE S_FareAttributes;



DROP TABLE IF EXISTS T_Currency;
DROP SEQUENCE S_Currency;



DROP TABLE IF EXISTS T_Calendar;
DROP SEQUENCE S_Calendar;



DROP INDEX IF EXISTS I_CalendarDates;
DROP TABLE IF EXISTS T_CalendarDates;
DROP SEQUENCE S_CalendarDates;



DROP INDEX IF EXISTS I_StopTimesSequence;
DROP TABLE IF EXISTS T_StopTimes;
DROP SEQUENCE S_StopTimes;


DROP INDEX IF EXISTS I_Stops;
DROP TABLE IF EXISTS T_Stops;
DROP SEQUENCE S_Stops;


DROP INDEX IF EXISTS I_Trips;
DROP TABLE IF EXISTS T_Trips;
DROP SEQUENCE S_Trips;

DROP INDEX IF EXISTS I_Routes;
DROP TABLE IF EXISTS T_Routes;
DROP SEQUENCE S_Routes;

DROP INDEX IF EXISTS I_CityAgency;
DROP TABLE IF EXISTS T_CityAgency;

DROP INDEX IF EXISTS I_City;
DROP TABLE IF EXISTS T_City;
DROP SEQUENCE S_City;

DROP INDEX IF EXISTS I_Agency;
DROP TABLE IF EXISTS T_Agency;
DROP SEQUENCE S_Agency;

DROP INDEX IF EXISTS I_DirectionType;
DROP TABLE IF EXISTS T_DirectionType;

DROP INDEX IF EXISTS I_TransportType;
DROP TABLE IF EXISTS T_TransportType;



