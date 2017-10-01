 package com.dlogger.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dlogger.service.DLogElasticsearchService;

@RestController
@RequestMapping("/dlogger/el/")
public class DLogElasticsearchRestController {
	
	@Autowired DLogElasticsearchService service;
	
	/*
	@RequestMapping("list")
	ResponseEntity<?> list() {
		return new ResponseEntity<>(logService.getPaged(0, 5), HttpStatus.OK);
	}
	*/
	
	@RequestMapping("list")
	ResponseEntity<?> list() {
		return new ResponseEntity<>(service.getPaged(0, 100), HttpStatus.OK);
	}
	
}
