package com.dlogger.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dlogger.service.DLogConfigurationService;

@RestController
@RequestMapping("/dlogger/conf/")
public class DLogConfigurationRestController {

    private static Logger logger = LoggerFactory.getLogger(DLogConfigurationRestController.class);

    @Autowired
    private DLogConfigurationService service;

    @RequestMapping(method = RequestMethod.GET, name = "conf")
    ResponseEntity<?> getConfiguration() {
        logger.info("Getting configuratio...");
        return new ResponseEntity<>(service.getConfiguration(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity<?> save(@RequestBody DLogConfigurationCommand command) {
        service.saveTimeToLive(command.getTimeToLive());
        return new ResponseEntity<>("Successfully saved", HttpStatus.OK);
    }

}
