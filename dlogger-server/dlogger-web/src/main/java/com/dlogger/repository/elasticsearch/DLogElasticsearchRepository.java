package com.dlogger.repository.elasticsearch;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DLogElasticsearchRepository extends ElasticsearchRepository<DLogEntry, String> {
	// Page<DLogEntry> findByMessage(String message, Pageable pageable);    
}
