package com.eventmanager.rest.access.backendrestservice.basicAuth;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eventmanager.rest.access.backendrestservice.eventList.Events;
@Repository
public interface UserJpaRepository extends JpaRepository<UserInfo,String> {	
	UserInfo findByUserName(String username);

}