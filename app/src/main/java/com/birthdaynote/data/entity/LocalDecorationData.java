package com.birthdaynote.data.entity;

public class LocalDecorationData {
    private long time;

    public LocalDecorationData() {
        this.time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LocalDecorationData{" +
                "time=" + time +
                '}';
    }
}
