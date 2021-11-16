package com.example.testrosbridge.msg;

import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "actuator/CreateMapResponse")
public class CreateMapResponse extends Message {
    // {"callback": 1}
    public int callback;
}
