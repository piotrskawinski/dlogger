package com.dlogger.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlogger.repository.hibernate.DLogConfiguration;
import com.dlogger.repository.hibernate.DLogConfigurationRepository;

@Service
public class DLogConfigurationService {

    private static Logger logger = LoggerFactory.getLogger(DLogConfigurationService.class);

    @Autowired
    private DLogConfigurationRepository repository;

    @Transactional
    public void saveTimeToLive(Integer timeToLive) {
        DLogConfiguration configuration = getConfiguration();
        configuration.setTimeToLive(timeToLive);
        logger.debug("Saving " + configuration.toString());
        repository.save(configuration);
    }

    public DLogConfiguration getConfiguration() {
        DLogConfiguration configuration;
        List<DLogConfiguration> configurations = repository.findAll();
        if (configurations != null && configurations.size() > 0) {
            configuration = configurations.get(0);
        } else {
            configuration = new DLogConfiguration();
        }
        logger.info("Retrieving configuration -> " + configuration.toString());
        return configuration;
    }

}
