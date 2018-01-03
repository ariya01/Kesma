package com.example.root.retrofit;

import java.util.List;

/**
 * Created by root on 1/3/18.
 */

public class DeviceModel {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<ReadDevice> getDevices() {
        return devices;
    }

    public void setDevices(List<ReadDevice> devices) {
        this.devices = devices;
    }

    String error;
    List<ReadDevice> devices;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    String message;
}
