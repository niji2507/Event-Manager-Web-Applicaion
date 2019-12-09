package com.eventmanager.rest.access.backendrestservice.eventList;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventJPARepository extends JpaRepository<Events,Long> {	
	

}
