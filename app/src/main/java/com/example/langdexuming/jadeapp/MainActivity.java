package com.example.langdexuming.jadeapp;

import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.langdexuming.jadeapp.R;
import com.example.langdexuming.jadeapp.fragment.content.FindContentFragment;
import com.example.langdexuming.jadeapp.fragment.content.IndexWebFragment;
import com.example.langdexuming.jadeapp.fragment.top.FindTopFragment;
import com.example.langdexuming.jadeapp.fragment.top.IndexTopFragment;
import com.example.langdexuming.jadeapp.fragment.top.MymacTopFragment;
import com.example.langdexuming.jadeapp.fragment.top.PersonCenterTopFragment;

import java.util.ArrayList;

public class    MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener,FindTopFragment.TopRefreshListener{

    /*Framgment碎片相关对象*/
    private IndexTopFragment e8xTop;
    private FindTopFragment e8xFindTop;
    private MymacTopFragment e8xMymacTop;
    private PersonCenterTopFragment e8xPersonCenterTop;
    private FragmentTransaction fragmentTransaction;

    /*fint_layout更新所用*/
    private FindContentFragment findContentFragment;

    /*WebView对象*/
    private WebView webView;

    /*二次返回退出程序*/
    private boolean isExit;

    /*当前页面*/
    private int pageNum=0;

    /*ViewPager相关对象*/
    private ViewPager viewpager;
    private String[] titles={"主页","发现","我的设备","个人中心"};
    private ArrayList<View> views=new ArrayList<>();
    public static ArrayList<View> index_product_views=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*自定义添加代码*/
        initPage(pageNum);//初始化需显示的页(ViewPager内部嵌套有viewpager时，需先初始化内部viewpager)
        initView();//初始化布局

    }
    public void initView(){
        viewpager=(ViewPager) findViewById(R.id.viewpager);
        /*将4个页面放入views*/
         views.add(getLayoutInflater().inflate(R.layout.index_layout, null));
        views.add(getLayoutInflater().inflate(R.layout.find_layout, null));
        views.add(getLayoutInflater().inflate(R.layout.mymac_layout, null));
        views.add(getLayoutInflater().inflate(R.layout.person_center_layout, null));
        viewpager.setAdapter(new MyPageAdapter());
        viewpager.setOnPageChangeListener(this);
        viewpager.setCurrentItem(pageNum);
    }
    /*初始化单个页面*/
    public void initPage(int pageNum) {
        /*初始化显示的页面*/
        switch (pageNum) {
        /*ViewPager适配器所需数组，需提前赋值*/
        /*1.添加views_product的布局*/
            case 0:
                index_product_views.add(getLayoutInflater().inflate(R.layout.product1_layout, null));
                index_product_views.add(getLayoutInflater().inflate(R.layout.product2_layout, null));
                index_product_views.add(getLayoutInflater().inflate(R.layout.product3_layout, null));
                index_product_views.add(getLayoutInflater().inflate(R.layout.product4_layout, null));
                index_product_views.add(getLayoutInflater().inflate(R.layout.product5_layout, null));
                index_product_views.add(getLayoutInflater().inflate(R.layout.product6_layout, null));
                index_product_views.add(getLayoutInflater().inflate(R.layout.product7_layout, null));
             break;
            default:
                return;
        }
        initTop(pageNum);//初始化页面顶部
        initBottom(pageNum);//初始化ViewPager页面固定底部
    }

    /*viewPager相关接口*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        pageNum=position;
        switch (position){
            /*切换index的Top*/
            case 0:
                e8xTop=new IndexTopFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_top,e8xTop);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            /*先检查网络，再切换find的Top*/
            case 1:
                find_load_repare();//需检查网络再加载 @开发阶段
                e8xFindTop=new FindTopFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_top,e8xFindTop);
                fragmentTransaction.addToBackStack(null);
                 fragmentTransaction.commit();
                break;
            /*切换mymac的Top*/
            case 2:
                e8xMymacTop=new MymacTopFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_top,e8xMymacTop);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            /*切换personCenter的Top*/
            case 3:
                e8xPersonCenterTop=new PersonCenterTopFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_top,e8xPersonCenterTop);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }
    }

    public void addClick(View v){//mymac_top.button
        /*创建对话框的构建器*/
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("添加设备");
        builder.setMessage("添加什么设备");
        builder.setIcon(R.mipmap.ic_launcher_logo);
        builder.show();//替代builder.onCreate().show();
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    public void find_load_repare(){
        /*1.检查网络状态*/
        if (!isNetworkConnected(this)) {
            Toast Toast = new Toast(this);
            Toast.makeText(getApplicationContext(), "无网络连接", Toast.LENGTH_SHORT).show();
            return;
        }
       /*2.判断网络连接类型*/
        int NetWorkID = ConnectedType(this);
        if (ConnectivityManager.TYPE_WIFI == NetWorkID)
            Toast.makeText(getApplicationContext(), "WIFI模式", Toast.LENGTH_SHORT).show();
        if (ConnectivityManager.TYPE_MOBILE == NetWorkID) {
            Toast.makeText(getApplicationContext(), "移动网络模式,功能暂未开放", Toast.LENGTH_SHORT).show();
            return;
        }
        /*3.为WIFI状态，继续页面加载*/
        Toast.makeText(this,"正在加载页面",Toast.LENGTH_LONG).show();

     }


    /*初始化页面顶部*/
    public void initTop(int pageNum){
        switch (pageNum){
            case 0:
                e8xTop = new IndexTopFragment();
                fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.fragment_top, e8xTop);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
            default:
                break;
    }
    }
    /*ViewPager底部初始化*/
    public void initBottom(int pageNum){
        switch(pageNum) {
            case 0:
                Button button1 = (Button) findViewById(R.id.button_index);
                Button button2 = (Button) findViewById(R.id.button_find);
                Button button3 = (Button) findViewById(R.id.button_mymac);
                Button button4 = (Button) findViewById(R.id.button_person_center);
                button1.setOnClickListener(this);
                button2.setOnClickListener(this);
                button3.setOnClickListener(this);
                button4.setOnClickListener(this);
                break;
            default:
                    break;
        }
    }
    /**
     *
     * @param context
     * @return
     */
    private boolean isNetworkConnected(Context context){
        if(context!=null){
            ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo!=null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }
    /**
     *
     * @param context
     * @return
     */
    private static int ConnectedType(Context context){
        if(context!=null){
            ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
            if(mNetworkInfo!=null&&mNetworkInfo.isAvailable()){
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_index:
                viewpager.setCurrentItem(0);
                break;
            case R.id.button_find:
                viewpager.setCurrentItem(1);
                break;
            case R.id.button_mymac:
                viewpager.setCurrentItem(2);
                break;
            case R.id.button_person_center:
                viewpager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void changeListView() {

        if(pageNum==1)//处于发现页面,调用该内部方法
        {
            findContentFragment = (FindContentFragment) getFragmentManager().findFragmentById(R.id.fragment_find_content);
            findContentFragment.refresh_list();
            Toast.makeText(this, "正在刷新列表，请等候", Toast.LENGTH_LONG).show();
        }
    }

    /*PagerAdapter为抽象类，必须实现其具体类*/
    class MyPageAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
        @Override
        public int getCount() {
            return views.size();
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(views.get(position));
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v=views.get(position);
            container.addView(v);
            return v;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if(pageNum==0){
                webView=(WebView)findViewById(R.id.webview_index);//处于该页面时,才获取该webView对象
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
            exit();
            return false;
        }
        else{
            return super.onKeyDown(keyCode,event);
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
    public void exit(){
        if(!isExit){
            isExit=true;
            Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            mHandler.sendEmptyMessageDelayed(0, 2000);//2s后复位（isExit=false）
        }else{
            /*退出到桌面*/
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            System.exit(0);

        }
    }
}
