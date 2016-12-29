package ro.gov.ithub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.gov.ithub.base.BaseServiceImpl;
import ro.gov.ithub.entity.Agency;
import ro.gov.ithub.repository.AgencyRepository;

@Service
public class AgencyServiceImpl extends BaseServiceImpl<Agency, Integer> implements AgencyService {

    @Autowired
    public AgencyServiceImpl(AgencyRepository agencyRepository) {
        super(agencyRepository);
    }

}
