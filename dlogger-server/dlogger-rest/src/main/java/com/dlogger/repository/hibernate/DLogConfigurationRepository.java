package com.dlogger.repository.hibernate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DLogConfigurationRepository extends JpaRepository<DLogConfiguration, Long> { 
	
}
