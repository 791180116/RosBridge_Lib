package com.example.testrosbridge;

import android.app.Application;

import com.jilk.ros.rosbridge.ROSBridgeClient;
import com.lc.rosbridge_lib.RosCUtil;

public class MyApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RosCUtil.getInstance()
                //.useEventBus()
                .init(this, "172.16.3.194", 9090);
    }

    @Override
    public void onTerminate() {
        if (RosCUtil.getInstance().isConnected()) {
            RosCUtil.getInstance().disconnect();
        }
        super.onTerminate();
    }

}



