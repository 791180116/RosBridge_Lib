package com.example.testrosbridge.msg;

import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

@MessageType(string = "actuator/AddPointRequest")
public class AddPointRequest extends Message {
    public boolean enable;// #true 保存并启用 false 记录当前点
    public int parkTime; //#停车时常
    public float limitValue; //#限速值 限速值表示在该路标和上一个路标之前此段区域内，车辆行驶速度小于等于限速值大小行驶，当限速值小于等于０时，当前路标为停车点，这时停车时常字段会生效．
    public AddPointAttr attr ;//#业务属性表
}
