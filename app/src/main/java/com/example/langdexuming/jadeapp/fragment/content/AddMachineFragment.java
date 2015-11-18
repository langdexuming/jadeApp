package com.example.langdexuming.jadeapp.fragment.content;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.langdexuming.jadeapp.R;
import com.example.langdexuming.jadeapp.data_class.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddMachineFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private RadioButton radioButton1,radioButton2,radioButton3;
    private RadioGroup radioGroup;
    private View view;

    private List<Map<String, Object>> typeMachines;
    private ListView nlistView;
    private nListViewAdapter nlistViewAdapter;
    private int flag=0;

    public AddMachineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.add_machine_layout, container, false);
        System.out.println("AddMachineFragment is ok!");
        typeMachines=getData(Machine.Machine_type.door);
        repare();
      /*  nlistView=new ListView(getActivity());
        nlistViewAdapter=new nListViewAdapter(getActivity(),typeMachines);
        nlistView.setAdapter(nlistViewAdapter);*/
        return view;
    }
    /**/
    private List<Map<String, Object>> getData(Machine.Machine_type machine_type){
         /*开发专用,直接赋值*/
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        switch(machine_type){
            case door:
                break;
            case room:
                break;
            case center:
                break;
            default:
                return null;

        }
        System.out.println(machine_type);
        return list;
    }
    /**/
    private void repare(){
        radioButton1=(RadioButton)view.findViewById(R.id.radio_machine_door);
        radioButton2=(RadioButton)view.findViewById(R.id.radio_machine_room);
        radioButton3=(RadioButton)view.findViewById(R.id.radio_machine_center);

        radioGroup=(RadioGroup)view.findViewById(R.id.radio_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        System.out.println(checkedId);
        switch (checkedId){
            case 0:
                System.out.println(checkedId);
                break;
            case R.id.radio_machine_door:
                System.out.println(checkedId);
                /*创建对话框的构建器*/
                AlertDialog.Builder dbuilder = new AlertDialog.Builder(getActivity());
                dbuilder.setTitle("选择门口机");
                dbuilder.setIcon(R.mipmap.ic_launcher_logo);
                dbuilder.setView(nlistView);
                dbuilder.show();
                break;
            case R.id.radio_machine_room:
                System.out.println(checkedId);
                AlertDialog.Builder rbuilder = new AlertDialog.Builder(getActivity());
                rbuilder.setTitle("选择室内机");
                rbuilder.setIcon(R.mipmap.ic_launcher_logo);
                rbuilder.setView(nlistView);
                rbuilder.show();
                break;
            case R.id.radio_machine_center:
                System.out.println(checkedId);
                AlertDialog.Builder cbuilder = new AlertDialog.Builder(getActivity());
                cbuilder.setTitle("选择中心机");
                cbuilder.setIcon(R.mipmap.ic_launcher_logo);
                cbuilder.setView(nlistView);
                cbuilder.show();
                break;
        }

    }

    public class nListViewAdapter extends BaseAdapter {
        private Context context;//运行上下文
        private List<Map<String, Object>> listItems;    //列表视图信息集合
        private LayoutInflater listContainer;//视图容器
        private boolean[] hasChecked;//被点击的标记变量

        public final class ListItemView{    //自定义控件集合
            private ImageView fragment_addmachine_img;
            private TextView fragment_addmachine_typeid;
        }
        /*自定义构造函数*/
        public nListViewAdapter(Context context, List<Map<String, Object>> listItems) {
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
                convertView = listContainer.inflate(R.layout.add_machine_layout, null);
                //获取控件对象
                listItemView.fragment_addmachine_img = (ImageView)convertView.findViewById(R.id.fragment_addmachine_img);
                listItemView.fragment_addmachine_typeid = (TextView)convertView.findViewById(R.id.fragment_addmachine_typeid);
                //设置控件集到convertView
                convertView.setTag(listItemView);
            }else {
                listItemView = (ListItemView)convertView.getTag();
            }

            //设置文字和图片
            listItemView.fragment_addmachine_img.setBackgroundResource((Integer) listItems.get(
                    position).get("fragment_addmachine_img"));
            listItemView.fragment_addmachine_typeid.setText((String) listItems.get(position)
                    .get("fragment_addmachine_typeid"));
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
