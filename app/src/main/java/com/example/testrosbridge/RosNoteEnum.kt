package com.example.testrosbridge

import com.example.testrosbridge.msg.*
import com.lc.rosbridge_lib.RosNote
import com.lc.rosbridge_lib.std_msgs.Int32

enum class RosNoteEnum(val rosNote: RosNote) {
    SubActuator(RosNote("/actuator", Actuator::class.java)),             //接收车端信息
    SubSlamapStatus(RosNote("/slamap_status", Int32::class.java)),   //接收地图创建完成信息

    SerAddPoint(RosNote("/zoneLimit", AddPointResponse::class.java)),    //添加巡检点Service
    SereCreateMap(RosNote("/slamap_srvs", CreateMapResponse::class.java)), //创建地图Service
}