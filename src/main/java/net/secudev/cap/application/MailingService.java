package net.secudev.cap.application;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.secudev.cap.application.event.changeWatch;

@Service
@Slf4j
public class MailingService implements IMailingService {
	
	@EventListener
	@Async
	public void onApplicationEvent(changeWatch event) {
		log.trace("event fired :"+event.getMessage());
		
	}

}
