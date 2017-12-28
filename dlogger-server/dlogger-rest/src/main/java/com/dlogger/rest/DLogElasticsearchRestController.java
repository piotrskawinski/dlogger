package com.dlogger.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dlogger.service.DLogElasticsearchService;

@RestController
@RequestMapping("/dlogger/")
public class DLogElasticsearchRestController {

    @Autowired
    DLogElasticsearchService service;

    @RequestMapping(method = RequestMethod.GET, path = "search")
    ResponseEntity<?> search(@RequestParam("types") String[] types, @RequestParam("q") String query) {
        return new ResponseEntity<>(service.search(types, query), HttpStatus.OK);
    }

}
