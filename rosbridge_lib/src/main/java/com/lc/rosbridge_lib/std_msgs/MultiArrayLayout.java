package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/MultiArrayLayout")
public class MultiArrayLayout extends Message {
	/**
	*  Array of dimension properties
	*/
	public MultiArrayDimension[] dim;
	
	/**
	*  padding elements at front of data
	*/
	public int data_offset;
	
}
