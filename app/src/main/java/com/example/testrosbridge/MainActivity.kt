package com.example.testrosbridge

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testrosbridge.R.layout
import com.jilk.ros.message.StdMsg
import com.lc.rosbridge_lib.RosCUtil
import com.example.testrosbridge.RosNoteEnum.SerAddPoint
import com.example.testrosbridge.msg.Actuator
import com.example.testrosbridge.msg.CreateMapRequest
import com.example.testrosbridge.msg.CreateMapResponse
import com.jilk.ros.message.ErrorMsg
import com.lc.rosbridge_lib.callback.RosServiceCallback
import com.lc.rosbridge_lib.callback.RosSubscribeCallback
import com.lc.rosbridge_lib.event.RosErrorEvent
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        //EventBus.getDefault().register(this);
    }

    fun Publish(view: View?) {
        val stdMsg = StdMsg()
        stdMsg.data = "hello publish"
        RosCUtil.getInstance().publish("/chatter", stdMsg)
    }

    fun Subscribe(view: View?) {
        //RosCUtil.getInstance().getClient().send(new Subscribe("/chatter", "订阅数据类型"));
        /*RosCUtil.getInstance().subscribe("/actuator", Actuator.class, new RosSubscribeCallback<Actuator>() {
            @Override
            public void success(Actuator response) {
                Log.i("Actuator", response.toString());
            }

            @Override
            public void error(ErrorMsg errorMsg) {

            }
        });*/
        RosCUtil.getInstance().subscribe(RosNoteEnum.SubActuator.rosNote,
            object : RosSubscribeCallback<Actuator> {
                override fun success(response: Actuator) {
                    Log.i("Actuator", response.toString())
                }

                override fun error(errorMsg: ErrorMsg) {}

            })
    }

    fun unSubscribe(view: View?) {
        RosCUtil.getInstance().unSubscribe(RosNoteEnum.SubActuator.rosNote)
        //RosCUtil.getInstance().disconnect();
        //RosCUtil.getInstance().unSubscribe("/actuator");//不生效
    }

    fun advertise(view: View?) {
        RosCUtil.getInstance().advertise("/actuator", Actuator::class.java)
    }

    fun unAdvertise(view: View?) {
        RosCUtil.getInstance().unAdvertise("/actuator", Actuator::class.java)
    }

    fun service(view: View?) {
        RosCUtil.getInstance().callWithHandler(SerAddPoint.rosNote,
            CreateMapRequest(),
            object : RosServiceCallback<CreateMapResponse> {
                override fun success(response: CreateMapResponse) {
                    Log.d("gettime", response.toString())
                    response.print()
                }

                override fun error(errorMsg: ErrorMsg) {
                    Log.e("err", errorMsg.toString())
                }
            })
    }

    @Subscribe
    fun onEvent(event: RosErrorEvent?) {
        //Log.d("Tag", event.toString());
    }
}