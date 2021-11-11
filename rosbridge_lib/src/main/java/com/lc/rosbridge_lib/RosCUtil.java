package com.lc.rosbridge_lib;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.jilk.ros.MessageHandler;
import com.jilk.ros.ROSClient;
import com.jilk.ros.Service;
import com.jilk.ros.Topic;
import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.Message;
import com.jilk.ros.rosbridge.ROSBridgeClient;
import com.jilk.ros.rosbridge.operation.Operation;
import com.jilk.ros.rosbridge.operation.Publish;
import com.jilk.ros.rosbridge.operation.Unsubscribe;
import com.lc.rosbridge_lib.callback.RosServiceCallback;
import com.lc.rosbridge_lib.callback.RosSubscribeCallback;
import com.lc.rosbridge_lib.EventDistribution;
import com.lc.rosbridge_lib.event.JsonObjectEvent;
import com.lc.rosbridge_lib.event.PublishEvent;
import com.lc.rosbridge_lib.event.RosErrorEvent;

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
    private EventBus eventBus;
    private boolean useEventBus;
    private EventDistribution eventDistribution;
    private RCConnectionListener rcConnectionListener;

    public static RosCUtil getInstance() {
        return RosCHolder.holder;
    }

    private static class RosCHolder {
        private static final RosCUtil holder = new RosCUtil();
    }

    public RosCUtil useEventBus(EventDistribution eventDistribution) {
        this.useEventBus = true;
        this.eventDistribution = eventDistribution;
        eventBus = EventBus.getDefault();
        eventBus.register(this);
        return this;
    }

    public RosCUtil setRCConnectionListener(RCConnectionListener rcConnectionListener) {
        this.rcConnectionListener = rcConnectionListener;
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
    public boolean onConnect() {
        if (client != null) {
            if (!isConnected) {
                return client.connect(new ROSClient.ConnectionStatusListener() {
                    @Override
                    public void onConnect() {
                        client.setDebug(BuildConfig.DEBUG);
                        client.setUseEventBus(useEventBus);
                        isConnected = true;
                        //((RCApplication) getApplication()).setRosClient(client);
                        //showTip("Connect ROS success");
                        Log.d("rosBridge", "Connect ROS success");
                        if (null != rcConnectionListener) {
                            rcConnectionListener.onConnect();
                        }
                    }

                    @Override
                    public void onDisconnect(boolean normal, String reason, int code) {
                        Log.d("rosBridge", "ROS disconnect");
                        isConnected = false;
                        if (null != rcConnectionListener) {
                            rcConnectionListener.onDisconnect(normal, reason, code);
                        }
                    }

                    @Override
                    public void onError(Exception ex) {
                        ex.printStackTrace();
                        if (null != rcConnectionListener) {
                            rcConnectionListener.onError(ex);
                        }
                        Log.d("rosBridge", "ROS communication error");
                    }
                });
            } else {
                return true;
            }
        } else {
            throw new NullPointerException("client == null,请在Application中初始化");
        }
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
        if (null != eventBus) {
            eventBus.unregister(this);
        }
        getClient().disconnect();
    }

    @Subscribe
    public void onEvent(final PublishEvent event) {
        Log.d(Tag, event.toString());
        if (eventDistribution != null) eventDistribution.onPublishEvent(event);
    }

    @Subscribe
    public void onEvent(final RosErrorEvent event) {
        Log.d(Tag, event.toString());
        if (eventDistribution != null) eventDistribution.onErrorEvent(event);
    }

    @Subscribe
    public void onEvent(final JsonObjectEvent event) {
        Log.d(Tag, event.toString());
        if (eventDistribution != null) eventDistribution.onJsonObjectEvent(event);
    }

    public interface RCConnectionListener {
        public void onConnect();

        public void onDisconnect(boolean normal, String reason, int code);

        public void onError(Exception ex);
    }
}
