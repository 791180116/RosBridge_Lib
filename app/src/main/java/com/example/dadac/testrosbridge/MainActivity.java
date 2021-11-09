package com.example.dadac.testrosbridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dadac.testrosbridge.RosBridgeActivity;
import com.jilk.ros.message.Empty;
import com.jilk.ros.message.ErrorMsg;
import com.jilk.ros.message.StdMsg;
import com.jilk.ros.rosapi.message.GetTime;
import com.jilk.ros.rosbridge.operation.Publish;
import com.jilk.ros.rosbridge.operation.Subscribe;
import com.jilk.ros.rosbridge.operation.Unsubscribe;
import com.lc.rosbridge_lib.RosCUtil;
import com.lc.rosbridge_lib.RosErrorEvent;
import com.lc.rosbridge_lib.RosServiceCallback;
import com.lc.rosbridge_lib.RosSubscribeCallback;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    private Button DC_Button_JumpToRos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DC_Button_JumpToRos = (Button) findViewById(R.id.DC_Button_JumpToRos);
        //EventBus.getDefault().register(this);
    }


    public void JumpToActivity(View view) {
        Intent myIntentRos = new Intent(MainActivity.this, RosBridgeActivity.class);
        startActivity(myIntentRos);
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