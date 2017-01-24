package ro.gov.ithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.gov.ithub.base.BaseController;
import ro.gov.ithub.entity.Trip;
import ro.gov.ithub.service.TripService;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class TripController extends BaseController<Trip> {

	private static final String TRIPS_EN = "/trips";
	private static final String TRIP_EN = "/trip";
	private static final String TRIP_EN_BY_ID = "/trip/{tripId}";

	@Autowired
	private TripService tripService;

	@RequestMapping(value = {TRIPS_EN}, method = GET)
	public Collection<Trip> getAllAgencies() {
		return tripService.findAll();
	}

	@RequestMapping(value = {TRIP_EN_BY_ID}, method = GET)
	public Trip getTrip(@PathVariable("tripId") final int tripId) {
		return tripService.findById(tripId);
	}

	@RequestMapping(value = {TRIP_EN}, method = POST)
	public void saveOrUpdateTrip(@RequestBody final Trip trip) {
		tripService.saveOrUpdate(trip);
	}

	@RequestMapping(value = {TRIP_EN}, method = DELETE)
	public void delete(@RequestBody final Trip trip) {
		tripService.delete(trip.getTripId());
	}
}
