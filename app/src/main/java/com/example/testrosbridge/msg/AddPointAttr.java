package com.example.testrosbridge.msg;

import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "actuator/AddPointAttr")
public class AddPointAttr extends Message {
    public String areaNumber;       //#区域编号
    public String pointNumber;      //#点位编号
    public int prePosition;         //#预置位
    public String eventType;        //#事件类型
    public int eventAttribute;      //#事件属性
}
