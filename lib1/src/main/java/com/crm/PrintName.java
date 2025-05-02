package com.crm;

import org.springframework.stereotype.Component;

@Component
public class PrintName {
    private final String name;

    public PrintName() {
        this.name = "CRM";
    }

    String print() {
        return "Hello, " + name + "!";
    }
}
