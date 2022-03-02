package com.aaronmegs.collnotice.common.exception;

public enum StateEnum {
    SUCCESS("success"),
    INFO("info"),
    WARN("warning"),
    ERROR("error");

    private String state;

    private StateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }
}
