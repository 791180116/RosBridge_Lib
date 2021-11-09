package com.lc.rosbridge_lib;

import android.app.Application;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.jilk.ros.MessageHandler;
import com.jilk.ros.ROSClient;
import com.jilk.ros.Service;
import com.jilk.ros.Topic;
import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.Message;
import com.jilk.ros.rosbridge.ROSBridgeClient;
import com.jilk.ros.rosbridge.operation.Advertise;
import com.jilk.ros.rosbridge.operation.Operation;
import com.jilk.ros.rosbridge.operation.Publish;
import com.jilk.ros.rosbridge.operation.Unadvertise;

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
    private ROSBridgeClient getClient() {
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

    private void send(Operation operation) {
        getClient().send(operation);
    }

    /**
     * 发布消息
     *
     * @param topic   消息地址
     * @param message 消息实体
     */
    public <T extends Message> void publish(String topic, T message) {
        Topic<T> pubTopic = new Topic(topic, message.getClass(), getClient());
        pubTopic.publish(message);
    }

    /**
     * 创建广播
     *
     * @param topic 广播地址
     * @param type  广播数据类型
     */
    public <T extends Message> void advertise(String topic, Class<? extends T> type) {
        Topic<T> advTopic = new Topic<>(topic, type, getClient());
        advTopic.advertise();
    }

    /**
     * 取消广播
     *
     * @param topic 广播地址
     * @param type  广播数据类型
     */
    public <T extends Message> void unAdvertise(String topic, Class<? extends T> type) {
        Topic<T> unAdvTopic = new Topic<>(topic, type, getClient());
        unAdvTopic.unadvertise();
    }

    /**
     * 订阅消息
     *
     * @param topic    topic地址
     * @param type     返回数据类型
     * @param callback 收到消息回调
     * @param <T>      返回数据类型
     */
    public <T extends Message> void subscribe(String topic, Class<? extends T> type, RosSubscribeCallback<T> callback) {
        Topic<T> subTopic = new Topic<>(topic, type, getClient());
        subTopic.subscribe(new MessageHandler<T>() {
            @Override
            public void onMessage(T message) {
                callback.success(message);
            }

            @Override
            public void onErrorMessage(ErrorMsg errorMsg) {

            }
        });
    }

    /**
     * 取消订阅消息
     *
     * @param topic topic地址
     * @param type  返回数据类型
     */
    public <T extends Message> void unSubscribe(String topic, Class<? extends T> type) {
        Topic<T> subTopic = new Topic<>(topic, type, getClient());
        subTopic.unsubscribe();
    }

    /**
     * 请求ros服务
     *
     * @param service      服务地址
     * @param responseType 放回数据类型
     * @param args         请求参数
     * @param callback     请求回调
     */
    public <CallType extends Message, ResponseType extends Message> void callWithHandler
    (String service, Class<? extends ResponseType> responseType, CallType args, RosServiceCallback<ResponseType> callback) {
        Service<CallType, ResponseType> callService = new Service(service, args.getClass(), responseType, getClient());
        callService.callWithHandler(args, new MessageHandler<ResponseType>() {
            @Override
            public void onMessage(ResponseType message) {
                callback.success(message);
            }

            @Override
            public void onErrorMessage(ErrorMsg errorMsg) {
                callback.error(errorMsg);
            }
        });
        //return service.callBlocking(args);
    }

    /**
     * 断开连接
     */
    public void disconnect() {
        getClient().disconnect();
    }

    @Subscribe
    public void onEvent(final RosErrorEvent event) {
        Log.e(Tag, event.toString());
    }
}
