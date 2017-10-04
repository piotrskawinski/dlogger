package com.dlogger.repository.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DLogElasticsearchRepository extends ElasticsearchRepository<DLogEntry, String> {

}
