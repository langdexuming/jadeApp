package com.example.langdexuming.jadeapp.fragment.content;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.DialogPreference;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.langdexuming.jadeapp.CallingActivity;
import com.example.langdexuming.jadeapp.MainActivity;
import com.example.langdexuming.jadeapp.R;
import com.example.langdexuming.jadeapp.data_class.Machine;
import com.example.langdexuming.jadeapp.data_class.Machine_typeid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class MymacContentFragment extends Fragment{
    private View v;
    private List<Map<String, Object>> MyMachine;
    private ListView listView;
    private mListViewAdapter mlistViewAdapter;
    private int flag=0;

    private String action;//CallActivity动作
        /*数据结构*/
    private ImageButton fragment_mymac_content_img;
    private  TextView fragment_mymac_content_name;
    private TextView fragment_mymac_content_loac;
    private LinearLayout fragment_mymac_content_linear;




    public MymacContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_mymac_content, container, false);
        /*init*/
        init();
        init_listView();
        return v;
    }
    private void init(){

    }
    public void refresh_list(){
        // System.out.println("refresh_list");
        //    AllMachine.clear();
        //   listView.removeAllViews();
        mlistViewAdapter.notifyDataSetChanged();
    }
    public void list_reduce(Map<String, Object> map){
        MyMachine.remove(map);
    }
    public void list_add(Map<String, Object> map){
        MyMachine.add(map);
    }
    /*refresh_listView()*/
    public void init_listView(){
        /*获取所需view*/
        fragment_mymac_content_img=(ImageButton)v.findViewById(R.id.fragment_mymac_content_img);
        fragment_mymac_content_name=( TextView)v.findViewById(R.id.fragment_mymac_content_name);
        fragment_mymac_content_loac=(TextView)v.findViewById(R.id.fragment_mymac_content_loc);
        fragment_mymac_content_linear=(LinearLayout)v.findViewById(R.id.fragment_mymac_content_linear);
        listView=(ListView)v.findViewById(R.id.listView_mymac);
        /*定义按钮监听事件*/
//        fragment_mymac_content_img.setOnClickListener(this);
        /*获取listView所需数据*/
        MyMachine=getData();

        /*实例化适配器对象*/
        mlistViewAdapter=new mListViewAdapter(getActivity(),MyMachine);
        listView.setAdapter(mlistViewAdapter);

    }
    private List<Map<String, Object>> getData() {
        /*开发专用,直接赋值*/
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        /*测试专用，遍历全部机器类型数组赋值*/
        for(Machine_typeid c:Machine_typeid.values()){
            Map<String, Object> map = new HashMap<String, Object>();
            Machine machine=new Machine(c);
            map.put("fragment_mymac_content_img", machine.getImgUrl());
            map.put("fragment_mymac_content_name",machine.getTypeId());
            map.put("fragment_mymac_content_loac",machine.getLocalAdress() );
            list.add(map);
        }
        return list;
        /*1.本地获取数据*/

        /*2.远程数据库获取*/

        /*3.在线网络获取*/
    }


    public class mListViewAdapter extends BaseAdapter {
        private Context context;//运行上下文
        private List<Map<String, Object>> listItems;    //列表视图信息集合
        private LayoutInflater listContainer;//视图容器
        private boolean[] hasChecked;//被点击的标记变量

        public final class mListItemView{    //自定义控件集合
            private ImageButton fragment_mymac_content_img;
            private  TextView fragment_mymac_content_name;
            private TextView fragment_mymac_content_loac;
            private LinearLayout fragment_mymac_content_linear;
        }
        /*自定义构造函数*/
        public mListViewAdapter(Context context, List<Map<String, Object>> listItems) {
            this.context = context;
            listContainer = LayoutInflater.from(context);   //创建视图容器并设置上下文
            this.listItems = listItems;
            hasChecked = new boolean[getCount()];
        }


        @Override
        public int getCount() {
            return listItems.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /*供适配器内部获取*/
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //    System.out.println("getView !");
            final int selectID = position;
            //自定义视图
            mListItemView  listItemView = null;
            if (convertView == null) {
                listItemView = new mListItemView();
                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.mymac_list_itemslayout, null);
                //获取控件对象
                listItemView.fragment_mymac_content_linear=(LinearLayout)convertView.findViewById(R.id.fragment_mymac_content_linear);
                listItemView.fragment_mymac_content_img = (ImageButton)convertView.findViewById(R.id.fragment_mymac_content_img);
                listItemView.fragment_mymac_content_name = ( TextView)convertView.findViewById(R.id.fragment_mymac_content_name);
                listItemView.fragment_mymac_content_loac = (TextView)convertView.findViewById(R.id.fragment_mymac_content_loc);
                //设置控件集到convertView
                convertView.setTag(listItemView);
            }else {
                listItemView = (mListItemView)convertView.getTag();
            }

            //设置文字和图片
            listItemView.fragment_mymac_content_img.setBackgroundResource((Integer) listItems.get(
                    position).get("fragment_mymac_content_img"));
            listItemView.fragment_mymac_content_name.setText((String) listItems.get(position)
                    .get("fragment_mymac_content_name"));
            listItemView.fragment_mymac_content_loac.setText((String) listItems.get(position).get("fragment_mymac_content_loac"));
            /*注册按钮事件*/
            listItemView.fragment_mymac_content_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /*创建对话框的构建器*/
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("设备操作");
                    builder.setIcon(R.mipmap.ic_launcher_logo);
       //             listItemView.fragment_mymac_content_linear.setBackgroundResource(R.color.blue);
                    /*新建-监听-通话-修改信息选项*/
                    String[] items={"监听","通话","修改信息","删除设备"};
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case 0:
                                    action="listen";
                                    ready_listen_machine();
                                    break;
                                case 1:
                                    action="call";
                                    ready_call_machine();
                                    break;
                                case 2:
                                    Map<String, Object> map = new HashMap<String, Object>();
                                    map.put("fragment_mymac_content_img", R.mipmap.ic_product1_1);
                                    map.put("fragment_mymac_content_name", "我添加的");
                                    map.put("fragment_mymac_content_loac", "中国");
                                    list_add(map);
                                    refresh_list();
                                    return;
                                case 3:
                                    Map<String, Object> map1 = new HashMap<String, Object>();
                                    map1.put("fragment_mymac_content_img", R.mipmap.ic_product1_1);
                                    map1.put("fragment_mymac_content_name", "我添加的");
                                    map1.put("fragment_mymac_content_loac", "中国");
                                    list_reduce(map1);
                                    refresh_list();
                                      Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_LONG).show();
                                   // MyMachine.remove(this.listItemView);
                                   // mlistViewAdapter.notifyDataSetChanged();
                                    return;
                            }
                            Intent intent=new Intent(getActivity(), CallingActivity.class);
                            intent.putExtra("action",action);//写入数据到intent对象
                            System.out.println("send:"+action);
                            startActivity( intent);
                        }
                    });


                 /*设置对话框*/
                    builder.show();
                }
            });

            return convertView;
        }
    }

    /**
     * listen
     */
    public void ready_listen_machine(){
        //to give a intent to callactivity to listten
    }

    /**
     * call
     */
    public void ready_call_machine(){
        //to give a intent to callactivity to call
    }



}
