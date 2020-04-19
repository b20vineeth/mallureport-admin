package com.easypick.web.events.vo;

public class KeyData implements Comparable<KeyData>{

	private String event;
	private Integer priority;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public int compareTo(KeyData o) {
		return this.getPriority().compareTo(o.getPriority());
	}
}
