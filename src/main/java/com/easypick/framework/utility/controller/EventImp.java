package com.easypick.framework.utility.controller;

import java.util.Map;

public interface EventImp {
	public void setListeners(Map<String, String> listeners) ;
	public Map<String, String> getListeners();
}
