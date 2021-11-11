package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;
import com.jilk.ros.rosbridge.indication.Base64Encoded;

@MessageType(string = "std_msgs/Int8MultiArray")
public class Int8MultiArray extends Message {
	/**
	*  specification of data layout
	*/
	public MultiArrayLayout layout;
	
	/**
	*  array of data
	*/
	@Base64Encoded
	public byte[] data;
	
}
