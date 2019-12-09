package com.eventmanager.rest.access.backendrestservice.eventList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationJpaRepository extends JpaRepository<Notification,Long> {
	
}
