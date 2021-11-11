package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/Duration")
public class Duration extends Message {
	public com.jilk.ros.message.Duration data;
	
}
