package com.dlogger.repository.elasticsearch;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "dlog", type = "internal")
public class DLogEntry {
    private Long id;
    private String level;
    private Date timestamp;
    private String message;

    public DLogEntry() {}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getLevel() { return level; }
	public void setLevel(String level) { this.level = level; }

	public Date getTimestamp() { return timestamp; }
	public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
    
}

