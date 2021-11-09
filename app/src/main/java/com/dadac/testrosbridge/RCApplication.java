package com.dadac.testrosbridge;

import android.app.Application;

import com.jilk.ros.rosbridge.ROSBridgeClient;
import com.lc.rosbridge_lib.RosCUtil;

/**
 * @ Create by dadac on 2018/10/8.
 * @Function:
 * @Return:
 */
public class RCApplication extends Application {


    ROSBridgeClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        RosCUtil.getInstance()
                //.useEventBus()
                .init(this, "172.16.3.194", 9090);
    }

    @Override
    public void onTerminate() {
        if (client != null)
            client.disconnect();
        super.onTerminate();

    }

    public ROSBridgeClient getRosClient() {
        return client;
    }

    public void setRosClient(ROSBridgeClient client) {
        this.client = client;
    }


}



