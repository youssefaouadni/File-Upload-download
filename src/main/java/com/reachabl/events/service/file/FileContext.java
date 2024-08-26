package com.reachabl.events.service.file;

public enum FileContext {
    USER_PROFILE_IMAGE("profile"),
    USER_SIGNATURE("signature");

    private final String name;


    FileContext(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
