package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/UInt16")
public class UInt16 extends Message {
	public short data;
	
}
