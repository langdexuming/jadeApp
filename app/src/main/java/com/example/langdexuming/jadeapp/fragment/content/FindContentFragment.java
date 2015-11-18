package com.example.langdexuming.jadeapp.fragment.content;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.DrawableRes;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
public class FindContentFragment extends Fragment {
    private View v;
    private List<Map<String, Object>> AllMachine;
    private ListViewAdapter listViewAdapter;
    private ListView listView;
    private int flag=0;

    /*开发阶段测试使用*/
   final int machine_num=7;
  /*  Machine_typeid[] machine_arr={Machine_typeid.R0705,Machine_typeid.R1001,
            Machine_typeid.R0707,Machine_typeid.R0707,Machine_typeid.D0701,
            Machine_typeid.D0304,Machine_typeid.D0109,Machine_typeid.D0108};*/


    /*数据结构*/
    private ImageView fragment_find_content_img;
    private TextView fragment_find_content_name;
    private TextView fragment_find_content_loac;


    public FindContentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_find_content,container,false);

        /*首次加载*/
        /*此处由于加载较长时间，需采用服务或者多线程方式更新界面*/
    /*    new Thread(new Runnable() {
            @Override
            public void run() {;
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                refresh_list();

            }
        }).start();*/

        init_list();

        return v;
    }
    public void refresh_list(){
       // System.out.println("refresh_list");
        //reGetData();
        listViewAdapter.notifyDataSetChanged();
    }
    private void list_reduce(Object object){

    }
    private void list_add(Object object){

    }

    public void init_list(){
        flag++;
        System.out.println("findLayout loaded"+flag+"times");
        /*1.检查是否开启WIFI,在MainActivity执行*/

        /*2.获取view*/
        fragment_find_content_img=(ImageView)v.findViewById(R.id.fragment_find_content_img);
        fragment_find_content_name=(TextView)v.findViewById(R.id.fragment_find_content_name);
        fragment_find_content_loac=(TextView)v.findViewById(R.id.fragment_find_content_loc);
        listView=(ListView)v.findViewById(R.id.listView_allMachine);
        /*定义按钮监听事件*/

        /*获取listView所需数据*/
        AllMachine=getData();

        /*实例化适配器对象*/
        listViewAdapter=new ListViewAdapter(getActivity(),AllMachine);

        listView.setAdapter(listViewAdapter);

    }
    private List<Map<String, Object>> getData() {
        /*开发专用,直接赋值*/
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        /*测试专用，遍历全部机器类型数组赋值*/
        for(Machine_typeid c:Machine_typeid.values()){
            Map<String, Object> map = new HashMap<String, Object>();
            Machine machine=new Machine(c);
            map.put("fragment_find_content_img", machine.getImgUrl());
            map.put("fragment_find_content_name",machine.getTypeId());
            map.put("fragment_find_content_loac",machine.getLocalAdress() );
            list.add(map);
        }
        return list;
        /*1.本地获取数据*/

        /*2.远程数据库获取*/

        /*3.在线网络获取*/
    }
    public class ListViewAdapter extends BaseAdapter{
        private Context context;//运行上下文
        private List<Map<String, Object>> listItems;    //列表视图信息集合
        private LayoutInflater listContainer;//视图容器
        private boolean[] hasChecked;//被点击的标记变量

        public final class ListItemView{    //自定义控件集合
            private ImageView fragment_find_content_img;
            private TextView fragment_find_content_name;
            private TextView fragment_find_content_loac;
        }
        /*自定义构造函数*/
        public ListViewAdapter(Context context, List<Map<String, Object>> listItems) {
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
            ListItemView  listItemView = null;
            if (convertView == null) {
                listItemView = new ListItemView();
                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.find_list_itemslayout, null);
                //获取控件对象
                listItemView.fragment_find_content_img = (ImageView)convertView.findViewById(R.id.fragment_find_content_img);
                listItemView.fragment_find_content_name = (TextView)convertView.findViewById(R.id.fragment_find_content_name);
                listItemView.fragment_find_content_loac = (TextView)convertView.findViewById(R.id.fragment_find_content_loc);
                //设置控件集到convertView
                convertView.setTag(listItemView);
            }else {
                listItemView = (ListItemView)convertView.getTag();
            }

            //设置文字和图片
            listItemView.fragment_find_content_img.setBackgroundResource((Integer) listItems.get(
                    position).get("fragment_find_content_img"));
            listItemView.fragment_find_content_name.setText((String) listItems.get(position)
                    .get("fragment_find_content_name"));
            listItemView.fragment_find_content_loac.setText((String) listItems.get(position).get("fragment_find_content_loac"));
            //注册按钮点击时间爱你
          /*  listItemView.detail.setOnClickListener(new View.OnClickListener() {
                @Override
                publicvoid onClick(View v) {
                    //显示物品详情
                    showDetailInfo(selectID);
                }
            });
            // 注册多选框状态事件处理
            listItemView.check
                    .setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
                        @Override
                        publicvoid onCheckedChanged(CompoundButton buttonView,
                                                    boolean isChecked) {
                            //记录物品选中状态
                            checkedChange(selectID);
                        }
                    });*/

            return convertView;
        }
    }


}
