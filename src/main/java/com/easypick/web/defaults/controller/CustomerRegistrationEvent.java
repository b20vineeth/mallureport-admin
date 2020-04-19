package com.easypick.web.defaults.controller;

public class CustomerRegistrationEvent {

    private String name;

    public CustomerRegistrationEvent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}