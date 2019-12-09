package com.eventmanager.rest.access.backendrestservice.basicAuth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationJpaRepository extends JpaRepository<UserNotification, Long> {
	  public List<UserNotification>findByUsername(String username);

	  public UserNotification findByUsernameAndNotificationId(String username, Long notificationId);
	  	
}
