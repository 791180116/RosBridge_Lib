package com.example.testrosbridge.msg;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "network/VoiceBroad")
public class VoiceBroad extends Message {
	/**
	* 播报次数
	*/
	public short Broadcast_Num;
	
	/**
	* 播报文件名长度
	*/
	public short Broadcast_ContentLen;
	
	/**
	* 播报文件名
	*/
	public String Broadcast_Content;
	
}
