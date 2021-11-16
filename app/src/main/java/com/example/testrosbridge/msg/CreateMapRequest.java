package com.example.testrosbridge.msg;

import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "actuator/CreateMapRequest")
public class CreateMapRequest extends Message {
    public  String file_name;
    public int command;
}
