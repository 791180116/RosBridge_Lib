package com.lc.rosbridge_lib;

import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.Message;

/**
 * rosService 回调
 *
 * @param <ResponseType>
 */
public interface RosSubscribeCallback<ResponseType extends Message> {
    void success(ResponseType response);
    void error(ErrorMsg errorMsg);
}
