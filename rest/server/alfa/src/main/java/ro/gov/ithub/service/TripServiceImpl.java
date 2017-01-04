package ro.gov.ithub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.gov.ithub.base.BaseServiceImpl;
import ro.gov.ithub.entity.Trip;
import ro.gov.ithub.repository.TripRepository;

@Service
public class TripServiceImpl extends BaseServiceImpl<Trip, Integer> implements TripService {

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        super(tripRepository);
    }
}
