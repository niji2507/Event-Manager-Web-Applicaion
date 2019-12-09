package com.eventmanager.rest.access.backendrestservice.eventList;

import org.springframework.web.bind.annotation.RestController;

import com.eventmanager.rest.access.backendrestservice.basicAuth.UserInfo;
import com.eventmanager.rest.access.backendrestservice.basicAuth.UserJpaRepository;
import com.eventmanager.rest.access.backendrestservice.basicAuth.UserNotification;
import com.eventmanager.rest.access.backendrestservice.basicAuth.UserNotificationJpaRepository;
import com.eventmanager.rest.access.backendrestservice.basicAuth.UserResponse;
import com.eventmanager.rest.access.backendrestservice.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EventListController {

	@Autowired
	private EventJPARepository eventJpaRepo;

	@Autowired
	private UserJpaRepository userJpaRepo;

	@Autowired
	private NotificationJpaRepository notificationJpaRepo;

	@Autowired
	UserNotificationJpaRepository usernotificationJpaRepo;

	@GetMapping("/event-list/{username}")
	public List<EventResponse> getAllEvents(@PathVariable String username) {
		List<EventResponse> eventList = new ArrayList<EventResponse>();
		try {
			List<Events> events = eventJpaRepo.findAll();
			for (Events event : events) {
				EventResponse eventResp = createEventResponseNode(event, username);
				eventList.add(eventResp);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return eventList;
	}

	private EventResponse createEventResponseNode(Events event, String username) {
		EventResponse respEvent = new EventResponse();
		boolean isRegistered = false;
		respEvent.setEventId(event.getEventId());
		respEvent.setCreatedBy(event.getCreatedBy());
		respEvent.setDescription(event.getDescription());
		respEvent.setDuration(event.getDuration());
		respEvent.setEventName(event.getEventName());
		respEvent.setFees(event.getFees());
		respEvent.setLocation(event.getLocation());
		respEvent.setTags(event.getTags());
		respEvent.setMaxParticipants(event.getMaxParticipants());
		Set<UserInfo> userInfo = event.getUser();
		List<String> userList = new ArrayList<String>();
		for (UserInfo info : userInfo) {
			if (info.getUserName().equalsIgnoreCase(username)) {
				isRegistered = true;
			}
			userList.add(info.getFirstName() + " "+ info.getLastName() + ":" + info.getEmail());
						
			
		}
		respEvent.setUserList(userList);
		respEvent.setRegistereduser(isRegistered);
		return respEvent;
	}

	@GetMapping("/event-list/{username}/{id}")
	public EventResponse getEvent(@PathVariable String username, @PathVariable long id) {
		EventResponse event = null;
		try {
			if (eventJpaRepo.existsById(id)) {
				event = createEventResponseNode(eventJpaRepo.findById(id).get(), username);
				return event;

			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return event;

	}

	@PutMapping("/event-list/{username}/{id}")
	public ResponseEntity<EventResponse> updateEvent(@PathVariable String username, @PathVariable long id,
			@RequestBody Events event) {
		EventResponse eventResp = null;
		try {
			if (eventJpaRepo.existsById(id)) {
				if (event.getCreatedBy().equalsIgnoreCase(username)) {
					Events existingEvent = eventJpaRepo.findById(id).get();
					event.setUser(existingEvent.getUser());
					Events updatedEvent = eventJpaRepo.save(event);
					Notification notification = new Notification(null, event.getEventId(), "Update",
							event.getEventName());
					Long notificationID = notificationJpaRepo.save(notification).getNotificationId();
					Set<UserInfo> userlist = existingEvent.getUser();
					for (UserInfo user : userlist) {
						if (!user.getUserName().equals(event.getCreatedBy())) {
							UserNotification userNotification = new UserNotification(null, user.getUserName(),
									notificationID, false);
							usernotificationJpaRepo.save(userNotification);
						}
					}

					eventResp = createEventResponseNode(updatedEvent, username);
				}
			} else {
				return new ResponseEntity<EventResponse>(eventResp, HttpStatus.NO_CONTENT);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<EventResponse>(eventResp, HttpStatus.OK);
	}

	@PostMapping("/event-list/{username}")
	public ResponseEntity<EventResponse> createEvent(@PathVariable String username, @RequestBody Events event) {
		EventResponse eventResp = null;
		try {
			if (event.getCreatedBy().equalsIgnoreCase(username)) {
				Events updatedEvent = eventJpaRepo.save(event);
				eventResp = createEventResponseNode(updatedEvent, username);
			} else {
				return new ResponseEntity<EventResponse>(eventResp, HttpStatus.NO_CONTENT);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<EventResponse>(eventResp, HttpStatus.OK);
	}

	@DeleteMapping("/event-list/{username}/{id}")
	public ResponseEntity<Void> deleteEvent(@PathVariable String username, @PathVariable long id) {
		try {
			if (eventJpaRepo.existsById(id)) {
				Events existingEvent = eventJpaRepo.findById(id).get();
				Set<UserInfo> userlist = existingEvent.getUser();
				Notification notification = new Notification(null, existingEvent.getEventId(), "Delete",
						existingEvent.getEventName());
				Long notificationID = notificationJpaRepo.save(notification).getNotificationId();
				for (UserInfo user : userlist) {
					if (!user.getUserName().equals(existingEvent.getCreatedBy())) {
						UserNotification userNotification = new UserNotification(null, user.getUserName(),
								notificationID, false);
						usernotificationJpaRepo.save(userNotification);
					}
				}
				eventJpaRepo.deleteById(id);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.noContent().build();

	}

	@PostMapping("/event/register")
	public ResponseEntity<EventUser> createEvent(@RequestBody EventUser eventUserMapping) {

		try {
			if (eventUserMapping.getEventId() == null) {
				throw new Exception("id is null");
			}
			if (eventUserMapping.getUsername() == null) {
				throw new Exception("username is null");
			}
			if (eventJpaRepo.existsById(eventUserMapping.getEventId())
					&& userJpaRepo.existsById(eventUserMapping.getUsername())) {
				Events existingEvent = eventJpaRepo.findById(eventUserMapping.getEventId()).get();
				UserInfo user = userJpaRepo.findById(eventUserMapping.getUsername()).get();
				existingEvent.getUser().add(user);
				user.getEvents().add(existingEvent);
				eventJpaRepo.save(existingEvent);

			} else {
				return new ResponseEntity<EventUser>(eventUserMapping, HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<EventUser>(eventUserMapping, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<EventUser>(eventUserMapping, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<EventUser>(eventUserMapping, HttpStatus.OK);
	}

	@GetMapping("/userEventList/{username}")
	public ResponseEntity<List<EventResponse>> getListOfUserEvents(@PathVariable String username) {

		List<EventResponse> eventListResp = new ArrayList<EventResponse>();
		try {
			if (username == null) {
				throw new Exception("username is null");
			}
			if (userJpaRepo.existsById(username)) {
				UserInfo user = userJpaRepo.findById(username).get();
				Set<Events> events = user.getEvents();
				for (Events event : events) {
					EventResponse eventResp = createEventResponseNode(event, username);
					eventListResp.add(eventResp);
				}

			} else {
				return new ResponseEntity<List<EventResponse>>(eventListResp, HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<List<EventResponse>>(eventListResp, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<EventResponse>>(eventListResp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<EventResponse>>(eventListResp, HttpStatus.OK);
	}

	@GetMapping("/eventUserList/{eventId}")
	public ResponseEntity<List<UserResponse>> getListOfUserEvents(@PathVariable Long eventId) {

		List<UserResponse> userListResp = new ArrayList<UserResponse>();
		try {
			if (eventId == null) {
				throw new Exception("id is null");
			}
			if (eventJpaRepo.existsById(eventId)) {
				Events event = eventJpaRepo.findById(eventId).get();
				Set<UserInfo> users = event.getUser();
				for (UserInfo user : users) {
					UserResponse UserResp = createUserResponseNode(user);
					userListResp.add(UserResp);
				}

			} else {
				return new ResponseEntity<List<UserResponse>>(userListResp, HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserResponse>>(userListResp, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<UserResponse>>(userListResp, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<UserResponse>>(userListResp, HttpStatus.OK);
	}

	@GetMapping("notifications/{username}")
	private List<Notification> getNotificationForUser(@PathVariable String username) {

		List<Notification> notificationList = new ArrayList<Notification>();
		try {
			List<UserNotification> userNotificationList = usernotificationJpaRepo.findByUsername(username);

			for (UserNotification userNotification : userNotificationList) {
				Notification notification = notificationJpaRepo.findById(userNotification.getNotificationId()).get();
				notificationList.add(notification);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return notificationList;
		} catch (Exception e) {
			return notificationList;
		}

		return notificationList;
	}

	@DeleteMapping("/notification/{username}/{notificationId}")
	private ResponseEntity<Void> deleleNotificationOfUser(@PathVariable String username,
			@PathVariable Long notificationId) {
		try {
			UserNotification userNotification = usernotificationJpaRepo.findByUsernameAndNotificationId(username,
					notificationId);
			if (userNotification != null) {
				usernotificationJpaRepo.deleteById(userNotification.getEntry());
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

	private UserResponse createUserResponseNode(UserInfo user) {
		UserResponse userResp = new UserResponse(user.getUserName(), user.getFirstName(), user.getLastName());
		return userResp;
	}

}
