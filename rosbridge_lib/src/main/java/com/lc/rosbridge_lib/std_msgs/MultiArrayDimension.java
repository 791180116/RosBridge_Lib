package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/MultiArrayDimension")
public class MultiArrayDimension extends Message {
	/**
	*  label of given dimension
	*/
	public String label;
	
	/**
	*  size of given dimension (in type units)
	*/
	public int size;
	
	/**
	*  stride of given dimension
	*/
	public int stride;
	
}
