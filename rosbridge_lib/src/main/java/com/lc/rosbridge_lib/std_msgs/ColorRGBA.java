package com.lc.rosbridge_lib.std_msgs;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "std_msgs/ColorRGBA")
public class ColorRGBA extends Message {
	public float r;
	
	public float g;
	
	public float b;
	
	public float a;
	
}
