package com.example.testrosbridge;


/**
 * Created by wangpf2019 on 2021/11/12.
 */
public enum Act {
    Aaa("String",Active.class),
    Baa("Integer",Active.class);

    public String name;
    public Class<? extends Message> type;



    Act(String string, Class<? extends Message> stringClass) {
        this.name = string;
        this.type = stringClass;
    }
}
class Mytest{
    public static void main(String[] args) {
        MyTest2 test2 = new MyTest2();
        test2.subscribe("123",Act.Aaa.type,new RosSubscribeCallback<>());

        Class<?> type = Act.Baa.type;
        System.out.println(type);
    }

}
class MyTest2{
    public <T extends Message> void subscribe(String topic, Class<? extends T> type, RosSubscribeCallback<T> callback) {
       return;
    }
}

class Message{

}
class Active extends Message{

}
class RosSubscribeCallback<T>{

}