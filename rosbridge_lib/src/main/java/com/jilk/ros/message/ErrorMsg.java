package com.jilk.ros.message;

/**
 * Created by lcc on 21-11-09.
 */
@MessageType(string = "err_msg")
public class ErrorMsg extends Message {
    private String values;

    public String getValues() {
        return values;
    }

    public ErrorMsg(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "values='" + values + '\'' +
                '}';
    }
}
