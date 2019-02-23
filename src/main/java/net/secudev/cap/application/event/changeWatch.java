package net.secudev.cap.application.event;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class changeWatch extends ApplicationEvent {

	private String message;

	public changeWatch(Object source, String message) {
		super(source);
		this.message = "Event fired "+source + " : " + message;
	}

	public String getMessage() {
		return message;
	}

}
