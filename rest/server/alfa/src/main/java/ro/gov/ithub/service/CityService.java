package ro.gov.ithub.service;

import ro.gov.ithub.base.BaseService;
import ro.gov.ithub.entity.City;

public interface CityService extends BaseService<City, Integer> {

    City findByCityName(String cityName);

    void delete(City city);
}
