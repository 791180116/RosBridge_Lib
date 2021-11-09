package com.lc.rosbridge_lib;

import android.app.Application;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.jilk.ros.MessageHandler;
import com.jilk.ros.ROSClient;
import com.jilk.ros.Service;
import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.Message;
import com.jilk.ros.rosbridge.ROSBridgeClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * ros通讯工具
 */
public class RosCUtil {
    private final String Tag = "RosCUtil";
    private Application context;
    private ROSBridgeClient client;
    private String mIp = "192.168.9.100";
    private int mPort = 9090;
    private boolean isConnected;

    public static RosCUtil getInstance() {
        return OkGoHolder.holder;
    }

    private static class OkGoHolder {
        private static final RosCUtil holder = new RosCUtil();
    }

    public RosCUtil useEventBus() {
        EventBus eventBus = EventBus.getDefault();
        eventBus.register(this);
        return this;
    }

    public void init(Application app) {
        init(app, mIp, mPort);
    }

    /**
     * 在Application中初始化
     *
     * @param app
     * @param ip   要连接的ip
     * @param port 要连接的端口
     */
    public void init(Application app, String ip, int port) {
        if (app == null) {
            Log.e(Tag, "app is null.");
            return;
        } else if (TextUtils.isEmpty(ip)) {
            Log.e(Tag, "app is null.");
            return;
        }
        if (context == null) {
            context = app;
            mIp = ip;
            mPort = port;
        }
        client = new ROSBridgeClient("ws://" + mIp + ":" + mPort);
        onConnect();
    }


    /**
     * @Function: 建立连接
     * @Return:
     */
    private boolean onConnect() {
        return client.connect(new ROSClient.ConnectionStatusListener() {
            @Override
            public void onConnect() {
                client.setDebug(BuildConfig.DEBUG);
                isConnected = true;
                //((RCApplication) getApplication()).setRosClient(client);
                //showTip("Connect ROS success");
                Log.d("rosBridge", "Connect ROS success");
            }


            @Override
            public void onDisconnect(boolean normal, String reason, int code) {
                Log.d("rosBridge", "ROS disconnect");
                isConnected = false;
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
                Log.d("rosBridge", "ROS communication error");
            }
        });
    }

    /**
     * @return rosBridge是否连通
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return ROSBridgeClient 实体
     */
    public ROSBridgeClient getClient() {
        if (null == client) {
            throw new NullPointerException("client == null,请在Application中初始化");
        }
        if (isConnected()) {
            return client;
        } else {
            if (onConnect()) {
                return client;
            } else {
                throw new RuntimeException("连接失败,请在检查ros服务是否开启");
            }
        }
    }

    public static void sent() {
        /*client.send();
        return new GetRequest<>(url);*/
    }

    public <CallType extends Message, ResponseType extends Message> void callWithHandler
            (String uri, Class<? extends ResponseType> responseType, CallType args, RosServiceCallback<ResponseType> callback) {
        Service<CallType, ResponseType> service = new Service(uri, args.getClass(), responseType, getClient());
        AsyncTask.execute(() -> {
            service.callWithHandler(args, new MessageHandler<ResponseType>() {
                @Override
                public void onMessage(ResponseType message) {
                    callback.success(message);
                }

                @Override
                public void onErrorMessage(ErrorMsg errorMsg) {
                    callback.error(errorMsg);
                }
            });
        });
        //return service.callBlocking(args);
    }

    @Subscribe
    public void onEvent(final RosErrorEvent event) {
        Log.e(Tag, event.toString());
    }
}
