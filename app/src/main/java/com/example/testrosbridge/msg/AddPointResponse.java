package com.example.testrosbridge.msg;

import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "actuator/AddPointResponse")
public class AddPointResponse extends Message {
    public boolean state;//true 成功 false 失败
}
