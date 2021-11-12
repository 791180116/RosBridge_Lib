# RosBridge_Lib 自用基于rosBridge实现android和ros间通信
## 添加引用
> android studio
 
#Step 1. 将 JitPack 存储库添加到您的构建文件中 
    
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

#Step 2. 添加依赖

    dependencies {
        implementation 'com.github.791180116:RosBridge_Lib:0.9'
    }
    
## 2.使用说明

本库主要是在ROSBridgeClient更改，内部用到了eventbus:3.0.0，fastjson:1.2.78，Java-WebSocket:1.5.2
主要方法封装类RosCUtil.java，熟悉ROSBridgeClient可以自行封装

>初始化

    RosCUtil.getInstance()
            //.useEventBus()                          //是否启用eventbus，在init()之前调用
            .init(this, "172.16.3.194", 9090);        //ip,端口

## Api
- 基础用法
  参考MainActivity

## 参考项目 ##
* [ROSBridgeClient](https://github.com/djilk/ROSBridgeClient)、 [TestRosBridge](https://github.com/KEYD111/TestRosBridge) 
