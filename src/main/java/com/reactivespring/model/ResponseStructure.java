package com.reactivespring.model;

public class ResponseStructure {
    private Object message;
    private boolean success;
    private int statusCode;

//    public ResponseStructure(String message, boolean success, int statusCode) {
//        this.message = message;
//        this.success = success;
//        this.statusCode = statusCode;
//    }


    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
