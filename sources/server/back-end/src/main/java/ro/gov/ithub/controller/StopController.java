package ro.gov.ithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.gov.ithub.base.BaseController;
import ro.gov.ithub.entity.Stop;
import ro.gov.ithub.service.StopService;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin
public class StopController extends BaseController<Stop> {

	private static final String STOPS_EN = "/stops";
	private static final String STOP_EN = "/stop";
	private static final String STOP_EN_BY_ID = "/stop/{stopId}";

	@Autowired
	private StopService stopService;

	@RequestMapping(value = {STOPS_EN}, method = GET)
	public Collection<Stop> getAllAgencies() {
		return stopService.findAll();
	}

	@RequestMapping(value = {STOP_EN_BY_ID}, method = GET)
	public Stop getStop(@PathVariable("stopId") final int stopId) {
		return stopService.findById(stopId);
	}

	@RequestMapping(value = {STOP_EN}, method = POST)
	public void saveOrUpdateStop(@RequestBody final Stop stop) {
		stopService.saveOrUpdate(stop);
	}

	@RequestMapping(value = {STOP_EN}, method = DELETE)
	public void delete(@RequestBody final Stop stop) {
		stopService.delete(stop.getStopId());
	}
}
