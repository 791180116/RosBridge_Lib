package com.example.testrosbridge.msg;
import com.jilk.ros.message.Header;
import com.jilk.ros.message.Message;
import com.jilk.ros.message.MessageType;

import java.util.Arrays;

@MessageType(string = "actuator/actuator")
public class Actuator extends Message {

	public Header header;
	
	/**
	* 车辆识别号码
	*/
	public String vin;
	
	/**
	* 电压
	*/
	public float vol;
	
	/**
	* 电量
	*/
	public float soc;
	
	/**
	* true 代表信号强 false 代表信号弱
	*/
	public boolean locationState;
	
	/**
	* 经度
	*/
	public double lon;
	
	/**
	* 纬度
	*/
	public double lat;
	
	/**
	* 方位角
	*/
	public double azimuth;
	
	/**
	* 危险等级 0 安全 1 警示 2陷困 (满足 1 或　2 刹车灯启动)
	*/
	public byte risk;
	
	/**
	* 轮角
	*/
	public float steer;
	
	/**
	* 瞬时车速km/h >0 前进 <0 后退
	*/
	public float speed;
	
	/**
	* 接管标志位 0: 自动驾驶  1:远程接管   2: WIFI 3: 充电 4: 手柄
	*/
	public byte takeOver;
	
	/**
	* 关机状态　true 准备关机　false 正常供电中
	*/
	public boolean shutDown;
	
	/**
	* 0 直行 -1 左转 1右转
	*/
	public byte steerState;
	
	/**
	* 0 停车 1 行驶
	*/
	public byte driveState;
	
	/**
	* 里程计
	*/
	public double mileage;
	
	/**
	* 0 作业中 1 返航充电中   2充电中 3 充电完成 4 返回作业中
	*/
	public byte workState;
	
	/**
	* 0 停障 1 左避障 2右避障 3自主避障
	*/
	public byte detourState;
	
	/**
	* true 安全 false危险  [0] 前方　[1]后方　[2] 左　[3]右
	*/
	public boolean[] security;
	
	/**
	* true 外部触发停车
	*/
	public boolean stoprTrigger;
	
	/**
	* 故障码
	*/
	public String fautlCoed;


	@Override
	public String toString() {
		return "Actuator{" +
				"header=" + header +
				", vin='" + vin + '\'' +
				", vol=" + vol +
				", soc=" + soc +
				", locationState=" + locationState +
				", lon=" + lon +
				", lat=" + lat +
				", azimuth=" + azimuth +
				", risk=" + risk +
				", steer=" + steer +
				", speed=" + speed +
				", takeOver=" + takeOver +
				", shutDown=" + shutDown +
				", steerState=" + steerState +
				", driveState=" + driveState +
				", mileage=" + mileage +
				", workState=" + workState +
				", detourState=" + detourState +
				", security=" + Arrays.toString(security) +
				", stoprTrigger=" + stoprTrigger +
				", fautlCoed='" + fautlCoed + '\'' +
				'}';
	}
}
