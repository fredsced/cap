package net.secudev.cap.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.secudev.cap.application.event.changeWatch;

@Service
@Slf4j
public class AdminService implements IAdminService {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void newUserCreated(final String message) {
		log.trace(message);
		changeWatch customSpringEvent = new changeWatch(this, message);
		applicationEventPublisher.publishEvent(customSpringEvent);
	}

}
