package com.machine.fcmpusherlib.api;

import com.machine.fcmpusherlib.BasicResponse;

public class ServerCommunicator {

    public static void getWebHook(Connector connector, String deviceToken) {
        connector.callServer(deviceToken+"", Connector.METHOD_GET, null, BasicResponse.class);
    }
}
