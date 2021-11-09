package com.lc.rosbridge_lib;

public class RosErrorEvent {
    private String id;
    private String values;
    private Boolean result;
    private String service;
    private String op;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValues() {
        return values;
    }

    public void setValues(String values) {
        this.values = values;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

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
