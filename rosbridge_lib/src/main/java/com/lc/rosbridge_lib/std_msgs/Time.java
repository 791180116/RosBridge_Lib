package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/Time")
public class Time extends Message {
	public com.jilk.ros.message.Time data;
	
}
