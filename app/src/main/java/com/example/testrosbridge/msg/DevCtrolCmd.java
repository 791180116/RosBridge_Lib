package com.example.testrosbridge.msg;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "network/DevCtrolCmd")
public class DevCtrolCmd extends Message {
	public int typeId;
	
	public int Dev_alarmLightId;
	
	/**
	* 警灯控制
	*/
	public Alarm Dev_alarmLightCmd;
	
	public int Dev_sirenId;
	
	/**
	* 警笛控制 0：关闭 1：开启
	*/
	public boolean Dev_sirenCmd;
	
	public int Dev_frontLightId;
	
	/**
	* 前灯, 0 :关闭, 1 :打开
	*/
	public boolean Dev_frontLightCmd;
	
	public int Dev_backLightId;
	
	/**
	* 后灯, 0 :关闭, 1 :开启
	*/
	public boolean Dev_backLightCmd;
	
	public int Dev_strongLightId;
	
	/**
	* 强光手电控制 0 :关闭 1 :打开
	*/
	public boolean Dev_strongLightCmd;
	
	public int Dev_turnLightId;
	
	/**
	* 转向灯控制 0：全部关闭 1:左转向开启 2:右转向开启 3:双闪
	*/
	public int Dev_turnLightCmd;
	
	public int Dev_LEDWordId;
	
	/**
	* LED文字显示
	*/
	public LEDWord Dev_LEDWord;
	
	public int Dev_LEDImageId;
	
	/**
	* LED图片显示
	*/
	public LEDImage Dev_LEDImage;
	
	public int Dev_LEDVideoId;
	
	/**
	* LED视频显
	*/
	public LEDVideo Dev_LEDVideo;
	
	public int Dev_LEDStopId;
	
	/**
	* 前屏：0x01，后屏：0x02，侧屏：0x03,全部:0xFE
	*/
	public short LED_StopLocationCmd;
	
	public int Dev_VoiceBroadcastId;
	
	/**
	* 语音播报控制
	*/
	public VoiceBroad Dev_VoiceBroadcast;
	
}
