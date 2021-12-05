package com.machine.fcmpusherlib;

import com.machine.fcmpusherlib.api.WebResponse;

public class BasicResponse extends WebResponse
{
    String status;
    String message;

    public BasicResponse() {
        this.status = "";
        this.message = "";
    }

    public BasicResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
