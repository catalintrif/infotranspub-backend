
INSERT INTO T_Stops(
    stop_id,
    code,
    name,
    description,
    lat,
    lon,
    zone_id,
    url,
    location_type,
    parent_station,
    timezone,
    wheelchair_boarding)
  SELECT
     stop_id,
    stop_code,
    stop_name,
    stop_desc,
    stop_lat,
    stop_lon,
    zone_id,
    stop_url,
    location_type,
    parent_station,
    stop_timezone,
    wheelchair_boarding
    from tmp_stops;

