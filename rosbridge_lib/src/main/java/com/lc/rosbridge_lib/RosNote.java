package com.lc.rosbridge_lib;

/**
 * topic：消息地址
 * type:返回消息类型
 */
public class RosNote {
    public Class type;
    public String topic;

    public RosNote(String topic, Class type) {
        this.topic = topic;
        this.type = type;
    }
}
