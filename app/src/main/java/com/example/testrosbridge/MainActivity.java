package com.example.testrosbridge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.jilk.ros.message.Empty;
import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.StdMsg;
import com.jilk.ros.rosapi.message.GetTime;
import com.lc.rosbridge_lib.RosCUtil;
import com.lc.rosbridge_lib.event.RosErrorEvent;
import com.lc.rosbridge_lib.callback.RosServiceCallback;
import com.lc.rosbridge_lib.callback.RosSubscribeCallback;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //EventBus.getDefault().register(this);
    }

    public void Publish(View view) {
        StdMsg stdMsg = new StdMsg();
        stdMsg.data = "hello publish";
        RosCUtil.getInstance().publish("/chatter", stdMsg);
    }

    public void Subscribe(View view) {
        //RosCUtil.getInstance().getClient().send(new Subscribe("/chatter", "订阅数据类型"));
        RosCUtil.getInstance().subscribe("/chatter", StdMsg.class, new RosSubscribeCallback<StdMsg>() {
            @Override
            public void success(StdMsg response) {
                Log.d("Subscribe", response.data);
            }

            @Override
            public void error(ErrorMsg errorMsg) {

            }
        });
    }

    public void unSubscribe(View view) {
        RosCUtil.getInstance().unSubscribe("/chatter", StdMsg.class);
    }

    public void service(View view) {
        RosCUtil.getInstance().callWithHandler("/chatter", GetTime.class, new Empty(), new RosServiceCallback<GetTime>() {
            @Override
            public void success(GetTime response) {
                Log.d("gettime", response.toString());
                response.print();
            }

            @Override
            public void error(ErrorMsg errorMsg) {
                Log.e("err", errorMsg.toString());
            }
        });
    }

    @org.greenrobot.eventbus.Subscribe
    public void onEvent(final RosErrorEvent event) {
        Log.d("Tag", event.toString());
    }
}
//