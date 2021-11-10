package com.lc.rosbridge_lib.event;

/**
 * 错误信息event
 */
public class RosErrorEvent {
    public String id;
    public String values;
    public Boolean result;
    public String service;
    public String op;

    @Override
    public String toString() {
        return "RosErrorEvent{" +
                "id='" + id + '\'' +
                ", values='" + values + '\'' +
                ", result=" + result +
                ", service='" + service + '\'' +
                ", op='" + op + '\'' +
                '}';
    }
}
