package com.example.testrosbridge.msg;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "network/LEDWord")
public class LEDWord extends Message {
	/**
	* 前屏：0x01，后屏：0x02，侧屏：0x03
	*/
	public short LEDWord_Location;
	
	/**
	* 宋体:0x01，黑体:0x02，楷体:0x03
	*/
	public short LEDWord_Font;
	
	/**
	* 红色：0x01，黄色：0x02，蓝色：0x03，绿色：0x04，白色：0x05
	*/
	public short LEDWord_Color;
	
	/**
	* 12号字体：0x01，14号字体：0x02，16号字体：0x03，18号字体：0x04，20号字体：0x05
	*/
	public short LEDWord_WordSize;
	
	/**
	* 静止：0x01，左进右出：0x02，右进左出：0x03，上进下出：0x04
	*/
	public short LEDWord_Mode;
	
	/**
	* 内容数据长度
	*/
	public short LEDWord_ContentLen;
	
	/**
	* 内容数据
	*/
	public String LEDWord_Content;
	
}
