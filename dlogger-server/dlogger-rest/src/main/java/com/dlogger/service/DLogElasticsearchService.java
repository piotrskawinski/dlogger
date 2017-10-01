package com.dlogger.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.dlogger.repository.elasticsearch.DLogElasticsearchRepository;
import com.dlogger.repository.elasticsearch.DLogEntry;

@Service
public class DLogElasticsearchService {	
	private static Logger logger = LoggerFactory.getLogger(DLogElasticsearchService.class);
	
	@Autowired private DLogElasticsearchRepository repository;
		
	public List<DLogEntry> getPaged(int page, int size) {
		List<DLogEntry> entries = repository.findAll(PageRequest.of(page, size)).getContent();
		logger.debug("Retrieved " + entries.size() + " log entries for [page: " + page + ", size: " + size + "]");
		return entries;
	}
			
}
