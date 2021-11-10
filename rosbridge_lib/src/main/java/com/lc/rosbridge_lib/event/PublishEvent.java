package com.lc.rosbridge_lib.event;

import com.jilk.ros.rosbridge.operation.Operation;

/**
 * EventBus event entity,describe ros server response info
 */

public class PublishEvent {
    public String msg;
    public String id;
    public String name;
    public String op;


    public PublishEvent(Operation operation, String name, String content) {
        if (operation != null) {
            id = operation.id;
            op = operation.op;
        }
        this.name = name;
        msg = content;
    }
}
