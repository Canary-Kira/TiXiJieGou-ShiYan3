package com.springboot.cs2contactdemo.BSDemo;

public class Response {
    private boolean success;

    public Response(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }
}
