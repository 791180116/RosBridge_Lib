package com.example.testrosbridge.msg;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "network/Alarm")
public class Alarm extends Message {
	/**
	*  0 关闭 1 打开
	*/
	public short status;
	
	/**
	* 频率闪烁
	*/
	public short hz;
	
}
