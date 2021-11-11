package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/Float64MultiArray")
public class Float64MultiArray extends Message {
	/**
	*  specification of data layout
	*/
	public MultiArrayLayout layout;
	
	/**
	*  array of data
	*/
	public double[] data;
	
}
