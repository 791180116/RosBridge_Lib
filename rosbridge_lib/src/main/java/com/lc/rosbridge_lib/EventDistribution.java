package com.lc.rosbridge_lib;

import com.lc.rosbridge_lib.event.JsonObjectEvent;
import com.lc.rosbridge_lib.event.PublishEvent;
import com.lc.rosbridge_lib.event.RosErrorEvent;

/**
 * event 事件分发
 * 实现本接口自定义，自定义分发用onJsonObjectEvent（）方法
 * 在初始化RosCUtil时设置
 */
interface EventDistribution {
    //接收ros消息报错时执行
    void onErrorEvent(RosErrorEvent errorEvent);

    //一般不用保留
    void onPublishEvent(PublishEvent publishEvent);

    //接收ros消息必然执行，返回原始JsonObject
    void onJsonObjectEvent(JsonObjectEvent jsonObject);
}
