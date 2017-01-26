package ro.gov.ithub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.gov.ithub.base.BaseController;
import ro.gov.ithub.entity.Agency;
import ro.gov.ithub.service.AgencyService;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
//TODO refine security for all REST services like this:
// @CrossOrigin(origins = {"http://localhost:63342", "http://localhost:8080", "http://localhost"})
@CrossOrigin
public class AgencyController extends BaseController<Agency> {

	private static final String AGENCIES_EN = "/agencies";
	private static final String AGENCY_EN = "/agency";
	private static final String AGENCY_EN_BY_ID = "/agency/{agencyId}";

	@Autowired
	private AgencyService agencyService;

	@RequestMapping(value = {AGENCIES_EN}, method = GET)
	public Collection<Agency> getAllAgencies() {
		return agencyService.findAll();
	}

	@RequestMapping(value = {AGENCY_EN_BY_ID}, method = GET)
	public Agency getAgency(@PathVariable("agencyId") final int agencyId) {
		return agencyService.findById(agencyId);
	}

	@RequestMapping(value = {AGENCY_EN}, method = POST)
	public void saveOrUpdateAgency(@RequestBody final Agency agency) {
		agencyService.saveOrUpdate(agency);
	}

	@RequestMapping(value = {AGENCY_EN}, method = DELETE)
	public void delete(@RequestBody final Agency agency) {
		agencyService.delete(agency.getAgencyId());
	}
}
