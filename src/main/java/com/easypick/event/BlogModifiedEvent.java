package com.easypick.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

public class BlogModifiedEvent extends ApplicationEvent {

	@EventListener
	@Async 
	private String events() {
		
		System.out.println("HIII-BlogModifiedEvent");
		return null;

	}

	public BlogModifiedEvent(Object source) {
		super(source);
		this.events();
	 }

}
