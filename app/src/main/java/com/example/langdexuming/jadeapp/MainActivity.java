package com.example.langdexuming.jadeapp;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private IndexTopFragment e8xTop;
    private IndextBottomFragment e8xBottom;
    private IndexContentFragment e8xContent;

    private SetContentFragment e8xSetContent;

    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    /*初始化*/
    public void init(){
        /*1.初始化布局*/
        e8xTop=new IndexTopFragment();
        e8xBottom=new IndextBottomFragment();
        e8xContent=new IndexContentFragment();
        fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_content, e8xContent);
        fragmentTransaction.add(R.id.fragment_bottom,e8xBottom);
        fragmentTransaction.add(R.id.fragment_top, e8xTop);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public void indexClick(View v){
        System.out.println("replace by index page");
        fragmentTransaction = getFragmentManager().beginTransaction();
        e8xTop = new IndexTopFragment();
        e8xBottom = new IndextBottomFragment();
        e8xContent = new IndexContentFragment();
        fragmentTransaction.replace(R.id.fragment_content, e8xContent);
        fragmentTransaction.replace(R.id.fragment_top, e8xTop);
        fragmentTransaction.replace(R.id.fragment_bottom, e8xBottom);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    public void setClick(View v){
        Toast.makeText(this,"test",Toast.LENGTH_LONG).show();
        System.out.println("replace by set page");
        fragmentTransaction = getFragmentManager().beginTransaction();
        e8xTop = new IndexTopFragment();
        e8xBottom = new IndextBottomFragment();
        e8xSetContent = new SetContentFragment();
        fragmentTransaction.replace(R.id.fragment_content, e8xSetContent);
        fragmentTransaction.replace(R.id.fragment_top, e8xTop);
        fragmentTransaction.replace(R.id.fragment_bottom, e8xBottom);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
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

}
