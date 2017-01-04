package ro.gov.ithub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.gov.ithub.base.BaseServiceImpl;
import ro.gov.ithub.entity.City;
import ro.gov.ithub.repository.CityRepository;

@Service
public class CityServiceImpl extends BaseServiceImpl<City, Integer> implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepo) {
        super(cityRepo);
        this.cityRepository = cityRepo;
    }

    @Override
    public City findByCityName(String cityShortName) {
        return cityRepository.findCityByCityShortName(cityShortName);
    }

    @Override
    public void delete(City city) {
        this.baseRepository.delete(city.getCityId());
    }

}
