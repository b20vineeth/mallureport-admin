package com.easypick.web.defaults.controller;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async
public class DataListener {
 
	
	@EventListener
	void handleRegistration(CustomerRegistrationEvent event){
	    System.out.println("Registration event got triggered for customer::  " + event.getName());
	}
	
 
	@EventListener
	void handleRegistration(String event){
	    System.out.println("event1:  " + event);
	}
	
	@EventListener
	void handleRegistration1(String event){
	    System.out.println("event2:  " + event);
	}
}