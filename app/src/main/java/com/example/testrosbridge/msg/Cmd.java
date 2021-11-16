package com.example.testrosbridge.msg;
import com.jilk.ros.message.Header;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "actuator/cmd")
public class Cmd extends Message {
	/**
	* 时间戳
	*/
	public Header header;
	
	/**
	* 当前设备id  selfDrive=0 cockpit=1 pad=2 charge=3 rawremote=4
	*/
	public byte id;
	
	/**
	* 是否使能 true使能 false 失能
	*/
	public boolean enable;
	
	/**
	* 开度 正值为前进，负值为后退 -100~100 m/s
	*/
	public float speed;
	
	/**
	* 车轮转角  -30~30度
	*/
	public float steer;
	
}
