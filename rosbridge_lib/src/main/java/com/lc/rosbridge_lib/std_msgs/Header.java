package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/Header")
public class Header extends Message {
	public int seq;
	
	public com.jilk.ros.message.Time stamp;
	
	public String frame_id;
	
}
