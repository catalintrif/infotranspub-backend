package ro.gov.ithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.gov.ithub.base.BaseController;
import ro.gov.ithub.entity.City;
import ro.gov.ithub.service.CityService;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CityController extends BaseController<City> {

    private static final String CITIES_EN = "/citylist";
    private static final String CITY_EN = "/city";
    private static final String CITY_EN_BY_NAME = "/city/{cityName}";
    private static final String CITY_EN_AGENCIES = "/city/{cityName}/agencylist";

    @Autowired
    private CityService cityService;

    @RequestMapping(value = {CITIES_EN}, method = GET)
    public Collection<City> getAllCities() {
        return cityService.findAll();
    }

    @RequestMapping(value = {CITY_EN_BY_NAME}, method = GET)
    public City getCity(@PathVariable("cityName") final String cityName) {
        return cityService.findByCityName(cityName);
    }

    @RequestMapping(value = {CITY_EN}, method = POST)
    public void saveOrUpdateCity(@RequestBody final City city) {
        cityService.saveOrUpdate(city);
    }

    @RequestMapping(value = {CITY_EN_AGENCIES}, method = GET)
    public City getAgenciesForCity(@PathVariable("cityName") final String cityName) {
        return cityService.findByCityName(cityName);
    }

    @RequestMapping(value = {CITY_EN}, method = DELETE)
    public void delete(@RequestBody final City city) {
        cityService.delete(city);
    }
}
