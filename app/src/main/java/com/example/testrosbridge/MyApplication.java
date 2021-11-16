package com.example.testrosbridge;

import android.app.Application;

import com.lc.rosbridge_lib.RosCUtil;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RosCUtil.getInstance()
                //.useEventBus()
                .setDebug(true)
                .init(this, "172.16.3.194", 9090);//192.168.9.102
                //.init(this, "192.168.9.102", 9090);
    }

    @Override
    public void onTerminate() {
        if (RosCUtil.getInstance().isConnected()) {
            RosCUtil.getInstance().disconnect();
        }
        super.onTerminate();
    }

}



