package com.example.testrosbridge.msg;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "network/LEDImage")
public class LEDImage extends Message {
	/**
	* 前屏：0x01，后屏：0x02，侧屏：0x03
	*/
	public short LEDImage_Location;
	
	/**
	* 静止：0x01，左进右出：0x02，右进左出：0x03，上进下出：0x04
	*/
	public short LEDImage_Mode;
	
	/**
	* 最小间隔：1秒，最大间隔：240秒
	*/
	public short LEDImage_TimInterval;
	
	/**
	* 内容数据长度
	*/
	public short LEDImage_ContentLen;
	
	/**
	* 内容数据
	*/
	public String LEDImage_Content;
	
}
