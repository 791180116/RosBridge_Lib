package com.lc.rosbridge_lib.callback;

import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.Message;

/**
 * rosSubscribe 回调
 *
 * @param <SubscribeType>
 */
public interface RosSubscribeCallback<SubscribeType extends Message> {
    void success(SubscribeType response);
    void error(ErrorMsg errorMsg);
}
