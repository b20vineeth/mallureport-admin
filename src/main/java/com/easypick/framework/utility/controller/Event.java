package com.easypick.framework.utility.controller;
 
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext; 
import org.springframework.stereotype.Service;

import com.easypick.web.events.vo.KeyData; 
@Service 
public class Event   {

	@Autowired
	ApplicationContext context;
	Map<String, List<KeyData>> listeners;
	 
	public Map<String, List<KeyData>> getListeners() {
		return listeners;
	}
 
	public void setListeners(Map<String, List<KeyData>> listeners) {
		this.listeners = listeners;
	}

	public Map<String, List<KeyData>> getEvent()
	{
		Event event = context.getBean("event", Event.class);
		return event.getListeners();

	}
}
