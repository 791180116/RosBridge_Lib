package com.example.testrosbridge.msg;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "network/LEDVideo")
public class LEDVideo extends Message {
	/**
	* 前屏：0x01，后屏：0x02，侧屏：0x03
	*/
	public short LEDVideo_Location;
	
	/**
	* 内容数据长度
	*/
	public short LEDVideo_ContentLen;
	
	/**
	* 内容数据
	*/
	public String LEDVideo_Content;
	
}
