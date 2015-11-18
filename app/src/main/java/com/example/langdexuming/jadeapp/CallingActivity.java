package com.example.langdexuming.jadeapp;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.example.langdexuming.jadeapp.data_class.Wifi_manage;
import com.example.langdexuming.jadeapp.data_class.e8x_udp_socket;

import java.io.File;

public class CallingActivity extends AppCompatActivity {
    /*WIFI通信相关对象*/
    //调试
    String TAG = "main";
    //应用对象
    Context ctx;
    //创建WIFI管理器
    Wifi_manage wifi_controller = null;
    //udp服务器对象
    e8x_udp_socket udp_server = null;
    //本机WIFI的IP地址
    int ip_addr;

    /*二次返回退出程序*/
    private boolean isExit;

    /*界面功能主要变量*/
    private Button button3_call;
    private String action;

    /*判断是否监听，通话，开锁*/
    private boolean isListen = false;
    private boolean isCall = false;
    private boolean isUnlock = false;

    /*媒体播放相关*/
    private MediaPlayer mp;
    private static final int MEDIA_TYPE_IMAGE = 1;
    private static final int MEDIA_TYPE_VIDEO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling);
        /*init*/
        /*init_udp*/
        init_udp_server();
        /**/
        button3_call = (Button) findViewById(R.id.button3_call);
        Intent intent = getIntent();//获取Intent对象
        action = intent.getStringExtra("action");
        System.out.println("receive:" + action);
        if (action.equals("call")) {
            call_machine();//呼叫
        }
        if (action.equals("listen")) {
            listen_machine();//监听
        }
    }
    /*初始化*/
    public void init_udp_server(){
        //创建本应用的wifi控制器
        wifi_controller = new Wifi_manage(ctx);

        //开启多播接收功能
        wifi_controller.allowMulticast();
        //获取IP
        ip_addr = wifi_controller.Get_WIFI_IP();
        Log.d(TAG, "获取IP地址为" + (ip_addr & 0xff) + "." + ((ip_addr >> 8) & 0xff)
                + "." + ((ip_addr >> 16) & 0xff) + "." + ((ip_addr >> 24) & 0xff));

        //udp服务器
        udp_server = new e8x_udp_socket(ctx,ip_addr);
        new Thread(udp_server).start();

    }

/*获取输出视频文件路径*/
    private Uri getOutputMediaFileUri(int type) {
        Uri uri = null;
        return uri;
    }
    /*获取输出视频文件*/
    private File getOutputMediaFile(int type) {
        File file = null;
        String rootPath = null;
        return file;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void listen_machine() {
        udp_server.Watch_test();//监听
        System.out.println("listen_machine");
        isListen = true;
    }

    private void stop_listen_machine() {
        System.out.println("STOP_listen_machine");
        isListen = false;
    }

    private void call_machine() {
        System.out.println("call_machine");
        button3_call.setText("挂断");
        Toast.makeText(this, "正在呼叫中...", Toast.LENGTH_LONG).show();
        isCall = true;//处于呼叫通话状态

    }

    private void stop_call_machine() {
        System.out.println("stop_call_machine");
        button3_call.setText("通话");
        Toast.makeText(this, "已挂断...", Toast.LENGTH_LONG).show();
        isCall = false;//处于挂断状态
    }

    private void unlock_machine() {
        System.out.println("unlock_machine");
        isUnlock = true;
    }

    private void lock_machine() {        //用于扩展
        System.out.println("lock_machine");
        isUnlock = false;
    }
//呼叫,挂断
    private void callClick(View v) {
        if (!isCall) {
            call_machine();
        /*    mp=MediaPlayer.create(this, R.raw.test);
            mp.start();*/
        } else {
            //   mp.stop();
            stop_call_machine();
        }
    }

    //开锁
    public void unlockClick(View v) {
        unlock_machine();
    }

    //拍照
    public void imageCaptureClick(View v) {
        udp_server.remote_openlock();
        /*Intent intent=new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);*/
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     *
     */
    public void exit() {
        if (isListen || isCall) {//有界面操作任务未完成
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "通话中，请勿挂断，2秒内再次点击，强行挂断！", Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(0, 2000);//2s后复位（isExit=false）
            } else {
            /*退出主界面*/
           /* Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);*/
            /*退出初始化*/
                stop_call_machine();//退出通话
                stop_listen_machine();//退出监听
            /*退出当前Activity*/
                finish();
            }
        } else {
            /*退出当前Activity*/
            finish();
        }
    }
    @Override
    protected void onDestroy() {

        udp_server.exit();
        //关闭多播接收功能
        wifi_controller.Multicast_close();
        super.onDestroy();
    }
}
