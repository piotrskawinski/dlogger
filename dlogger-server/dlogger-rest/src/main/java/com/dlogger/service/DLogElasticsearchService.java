package com.dlogger.service;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.dlogger.repository.elasticsearch.DLogElasticsearchRepository;
import com.dlogger.repository.elasticsearch.DLogEntry;

@Service
public class DLogElasticsearchService {
    private static Logger logger = LoggerFactory.getLogger(DLogElasticsearchService.class);

    @Autowired
    private DLogElasticsearchRepository repository;

    public List<DLogEntry> search(String[] types, String query) {
    	SearchQuery searchQuery = new NativeSearchQueryBuilder()
    			.withPageable(PageRequest.of(0, 1000))
    			.withIndices("dlog")
    			.withTypes(types)
    			.withQuery(QueryBuilders.wildcardQuery("message", query))    			
    			.build();
    	
        List<DLogEntry> entries = repository.search(searchQuery).getContent();
        logger.debug("Retrieved matched " + entries.size() + " log entries");
        return entries;
    }

}
