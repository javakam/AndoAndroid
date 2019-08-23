package com.improve.modules.eventbus3;

public class MessageEvent {

    private String message;
    private String time;

    public MessageEvent(String message, String time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}