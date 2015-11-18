package com.example.langdexuming.jadeapp.data_class;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;


/**
 *  Created by Steven on 2015/10/27.
 *  UDP_Server
 */
public class e8x_udp_socket implements Runnable{

    //本程序
    Context ctx = null;
    //标识符
    String TAG = "udp";
    //发送线程
    //手机的ip地址
    int ip_addr;
    //E8X的UDP头编码
    byte head_code[] = new byte[6];
    //本机地址编码  -- 就家庭住址
    byte Local_addr[] = new byte[20];
    //本地IP地址
    byte Local_ip[] = new byte[4];
    //远程地址
    byte remote_addr[] = new byte[12];
    //远程IP地址
    byte remote_ip[] = new byte[4];
    //创建套接字
    DatagramSocket recv_socket = null;
    DatagramSocket send_socket = null;
    public e8x_udp_socket(Context ctx,int ip_addr)
    {
        this.ctx = ctx;
        this.ip_addr = ip_addr;
        //获取本机IP地址用
        this.Local_ip[0] = (byte)(ip_addr & 0xff);
        this.Local_ip[1] = (byte)((ip_addr >> 8) & 0xff);
        this.Local_ip[2] = (byte)((ip_addr >> 16) & 0xff);
        this.Local_ip[3] = (byte)((ip_addr >> 24) & 0xff);
        //设置UDP头
        head_code[0] = 'X';
        head_code[1] = 'X';
        head_code[2] = 'X';
        head_code[3] = 'C';
        head_code[4] = 'I';
        head_code[5] = 'D';
        //设置远程地址
        this.remote_addr[0] = 'M';
        this.remote_addr[1] = '0';
        this.remote_addr[2] = '0';
        this.remote_addr[3] = '0';
        this.remote_addr[4] = '1';
        this.remote_addr[5] = '0';
        this.remote_addr[6] = '1';
        this.remote_addr[7] = '0';
        this.remote_addr[8] = '0';
        this.remote_addr[9] = '0';
        this.remote_addr[10] = '0';
        this.remote_addr[11] = '0';
        //设置远程IP地址
        this.remote_ip[0] = (byte)192;
        this.remote_ip[1] = (byte)168;
        this.remote_ip[2] = (byte)0;
        this.remote_ip[3] = (byte)131;
        //初始化本机地址
        init_addr();
        //初始化套接字
        try
        {
            send_socket = new DatagramSocket();
            Log.d(TAG,"成功建立发送套接字");
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
        try
        {
            recv_socket = new DatagramSocket(8302);
            Log.d(TAG,"成功初始化接收套接字");
        }
        catch (SocketException e)
        {
            e.printStackTrace();
        }
    }
    //线程运行方法
    public void run()
    {
        byte[] read_data = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(read_data,
                read_data.length);
        while(true)
        {
            try {
                recv_socket.receive(datagramPacket);
                /*
                Log.d(TAG, "获取视频udp包" + datagramPacket.getAddress()
                        .getHostAddress().toString()
                        + ":" + new String(datagramPacket.getData()));
                */
                VideoPortDeal(datagramPacket.getAddress(),read_data);
            }
            catch (IOException e)
            {
                //e.printStackTrace();
            }
        }
    }
    //发送数据 测试用
    public void send_test()
    {
        new Thread(new Runnable() {
            public void run() {
                String message = "test";
                int test_port = 8302;   //测试端口
                InetAddress local = null;

                try {
                    // 换成服务器端IP
                    local = InetAddress.getByName("localhost");
                    Log.d(TAG,"获取本机ip" + local.getAddress().toString());
                    } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                int msg_length = message.length();
                byte[] messagemessageByte = message.getBytes();
                DatagramPacket p = new DatagramPacket(messagemessageByte, msg_length, local,
                        8302);

                try {
                    send_socket.send(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    //退出的时候把套接字关闭
    public void exit()
    {
        //
        recv_socket.close();
        Log.d(TAG, "关闭接收套接字");
        send_socket.close();
        Log.d(TAG, "关闭发送套接字");
    }
    //初始化的家庭住址
    public void init_addr()
    {
        Local_addr[0] = 'S';    //室内机
        Local_addr[1] = 0;      //1栋
        Local_addr[2] = 0;
        Local_addr[3] = 0;
        Local_addr[4] = 1;

        Local_addr[5] = 0;      //1单元
        Local_addr[6] = 1;

        Local_addr[7] = 0;      //0101
        Local_addr[8] = 1;
        Local_addr[9] = 0;
        Local_addr[10] = 1;

        Local_addr[11] = 0;     //设备号设置为0
    }
    //8302端口进入
    void VideoPortDeal(InetAddress from_ip,byte[] buff)
    {
        //
        switch (buff[6])
        {
            case (byte)154:  //主机名解析
                switch(buff[7] & 0x03)
                {
                    case 1: //解析请求
                            if(buff.length >= 18)    //数据正常
                            {

                            }
                        break;
                    case 2: //解析应答
                            if(buff.length >= 18)  //数据正常
                            {

                            }
                        break;
                }
                break;
            case (byte)152: //局域网监控
                switch(buff[8])
                {
                    case 2: //对方正忙 占线
                        break;
                    case 4: //呼叫应答  被叫方应答本方
                        break;
                    case 9: //在线确认包
                        break;
                    case 30:    //通话结束
                        break;
                    case 7:     //通话上行
                    case 8:     //通话下行
                        RecvWatchCallUpDown_Func(from_ip,buff);
                        break;
                }
                break;
        }
    }
    //接收到音视频数据的
    public void RecvWatchCallUpDown_Func(InetAddress from_ip,byte[] buff)
    {
        Log.d(TAG, "收到音视频数据包");
        //判断是否为监视状态
        switch (buff[61])
        {
            case 2://视频  I帧  352*288
            case 3://视频  P帧  352*288
            case 4://视频  I帧  720*480
            case 5://视频  P帧  720*480
                VideoDeal_Func(from_ip,buff);
                break;
        }
    }
    //视频处理
    public void VideoDeal_Func(InetAddress from_ip,byte[] buff)
    {
        int talk_frameno,talk_currpackage,talk_totalpackage,talk_packlen,talk_datalen,talk_framelen;

        //帧序号
        talk_frameno = buff[64];
        talk_frameno <<= 8;
        talk_frameno += buff[63];

        //总包数
        talk_totalpackage = buff[70];
        talk_totalpackage <<= 8;
        talk_totalpackage += buff[69];

        //当前包数
        talk_currpackage = buff[72];
        talk_currpackage <<= 8;
        talk_currpackage = buff[71];

        //数据长度
        talk_datalen = buff[74];
        talk_datalen <<= 8;
        talk_datalen += buff[73];

        //数据包最大值
        talk_packlen = buff[76];
        talk_packlen <<= 8;
        talk_packlen += buff[75];

        //帧长度
        talk_framelen = buff[66];
        talk_framelen <<= 8;
        talk_framelen += buff[65];



    }
    //监视功能
    public void Watch_Func()
    {
        byte[] sendb = new byte[128];

        switch (Local_addr[0])
        {
            case 'S':   //室内机

                break;
            case 'B':
                break;
        }
    }
    //接收到主机解析请求的处理
    public void RecvNSAsk_Func(InetAddress from_ip,byte[] buff)
    {
        byte isAddrOK;
        isAddrOK = 1;
        //判断主叫地址是否为本机地址
        for(int i = 0;i < Local_addr.length;i++)
        {
            if(Local_addr[i] != buff[i + 8])
            {
                //主叫地址不匹配
                isAddrOK = 0;
                break;
            }
        }
        if(isAddrOK == 0) {
            isAddrOK = 1;
            for(int i = 0;i < Local_addr.length;i++)
            {
                //判断被叫地址是否为本机地址
                if(Local_addr[i] != buff[i + 32])
                {
                    isAddrOK = 0;   //不是
                    Log.d(TAG,"被叫地址不是本机地址");
                    break;
                }
            }
            //本机地址被地址解析请求
            if(isAddrOK == 1)
            {
                byte[] send_data = new byte[57];
                for(int i = 0;i < 32;i++)
                {
                    send_data[i] = buff[i]; //发送请求包和会送请求包前32位时一样的
                }
                send_data[7] = (byte)(0x80 | 2);    //表示应答包
            }
        }
    }

    //监视测试函数
    public void Watch_test()
    {
        byte[] sendb = new byte[1024];
        for(int i = 0;i < 6;i++)
        {
            sendb[i] = head_code[i];
        }

        //监视命令
        sendb[6] = (byte)152;
        Log.d(TAG,"强制转换后" + sendb[6]);
        //监视请求
        sendb[7] = (byte)(0x80 | 1);
        //呼叫
        sendb[8] = 1;
        //加入本地地址
        for(int i = 0;i < 20;i++)
        {
            sendb[i + 9] = Local_addr[i];
        }
        //加入本地IP地址
        for(int i = 0;i < 4;i++)
        {
            sendb[i + 29] = Local_ip[i];
        }

        for(int i = 0;i < 12;i++)
        {
            sendb[33 + i] = remote_addr[i];
        }
        /*
        //加入门口机IP地址
        sendb[53] = (byte)192;
        sendb[54] = (byte)168;
        sendb[55] = (byte)0;
        sendb[56] = (byte)131;
        */
        for(int i = 0;i < 4;i++)
        {
            sendb[53 + i] = remote_ip[i];
        }

        send_data_udp(sendb,8302,62);


    }

    //远程开锁方法
    public void remote_openlock()
    {
        byte[] sendb = new byte[128];
        //设置包头
        for(int i = 0;i < 6;i++)
        {
            sendb[i] = head_code[i];
        }
        //局域网可视对讲命令
        sendb[6] = (byte)150;
        //请求
        sendb[7] = (byte)(0x80 | 1);
        //操作命令  10:远程开锁
        sendb[8] = (byte)10;
        //加入本地地址
        for(int i = 0;i < 20;i++)
        {
            sendb[i + 9] = Local_addr[i];
        }
        //加入本地IP地址
        for(int i = 0;i < 4;i++)
        {
            sendb[i + 29] = Local_ip[i];
        }
        for(int i = 0;i < 12;i++)
        {
            sendb[33 + i] = remote_addr[i];
        }

        //加入门口机IP地址

        for(int i = 0;i < 4;i++)
        {
            sendb[53 + i] = remote_ip[i];
        }
        send_data_udp(sendb,8302,57);
    }

    //监视挂断方法
    public void cut_out()
    {
          byte sendb[] = new byte[128];
        //设置包头
        for(int i = 0;i < 6;i++)
        {
            sendb[i] = head_code[i];
        }
        //局域网监视
        sendb[6] = (byte)152;
        //请求
        sendb[7] = (byte)(0x80 | 1);
        //操作命令  30:通话结束
        sendb[8] = (byte)30;
        //加入本地地址
        for(int i = 0;i < 20;i++)
        {
            sendb[i + 9] = Local_addr[i];
        }
        //加入本地IP地址
        for(int i = 0;i < 4;i++)
        {
            sendb[i + 29] = Local_ip[i];
        }
        for(int i = 0;i < 12;i++)
        {
            sendb[33 + i] = remote_addr[i];
        }

        //加入门口机IP地址
        for(int i = 0;i < 4;i++)
        {
            sendb[53 + i] = remote_ip[i];
        }
        send_data_udp(sendb,8302,57);
    }

    //发送数据方法
    public void send_data_udp(final byte[] sendb,final int port,final int len)
    {
        new Thread(new Runnable() {
            public void run() {
                InetAddress local = null;

                try {
                    // 换成服务器端IP
                    local = InetAddress.getByName("192.168.0.131");
                    Log.d(TAG,"门口机地址" + local.getAddress().toString());
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                DatagramPacket p = new DatagramPacket(sendb, len, local,
                        port);

                try {
                    send_socket.send(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
