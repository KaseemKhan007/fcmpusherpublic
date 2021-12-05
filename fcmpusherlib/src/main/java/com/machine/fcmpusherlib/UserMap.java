package com.machine.fcmpusherlib;

import java.io.Serializable;

/**
 * Created by admin on 30/10/2017.
 */

public class UserMap implements Serializable {

    private String id;
    private String displayValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}
