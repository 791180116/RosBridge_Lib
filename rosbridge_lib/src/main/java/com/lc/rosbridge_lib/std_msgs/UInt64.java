package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/UInt64")
public class UInt64 extends Message {
	public long data;
	
}
