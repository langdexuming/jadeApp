package com.example.langdexuming.jadeapp.data_class;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.net.InetAddress;

/**
 * Created by Steven on 2015/10/28.
 */
public class Wifi_manage {
    //保存本应用
    Context ctx;
    WifiManager wifiManager = null;

    //Wifi组播锁定管理器
    WifiManager.MulticastLock multicastLock;
    public Wifi_manage(Context ctx)
    {
        this.ctx = ctx;
        //获取本应用的WIFI管理器
        wifiManager =  (WifiManager) ctx.getSystemService(ctx.WIFI_SERVICE);

    }
    //开启组播功能
    public void allowMulticast() {

        multicastLock = wifiManager.createMulticastLock("multicast.test");
        multicastLock.acquire();
    }

    //关闭组播功能
    public void Multicast_close()
    {
        //释放多播锁
        multicastLock.release();
    }
    //获取WIFI连接的IP
    public int Get_WIFI_IP()
    {
        //获取WIFI连接的信息
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return wifiInfo.getIpAddress();
    }
}
