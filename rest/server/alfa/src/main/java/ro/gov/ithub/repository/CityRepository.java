package ro.gov.ithub.repository;

import ro.gov.ithub.base.BaseRepository;
import ro.gov.ithub.entity.City;

public interface CityRepository extends BaseRepository<City, Integer> {

    City findCityByCityShortName(String shortName);

}
