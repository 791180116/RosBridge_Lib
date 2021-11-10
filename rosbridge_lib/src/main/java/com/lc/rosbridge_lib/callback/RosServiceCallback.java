package com.lc.rosbridge_lib.callback;

import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.Message;

/**
 * rosService 回调
 *
 * @param <ResponseType>
 */
public interface RosServiceCallback<ResponseType extends Message> {
    void success(ResponseType response);
    void error(ErrorMsg errorMsg);
}
