package com.example.langdexuming.jadeapp.fragment.top;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.langdexuming.jadeapp.AddMachineActivity;
import com.example.langdexuming.jadeapp.R;
import com.example.langdexuming.jadeapp.fragment.content.AddMachineFragment;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MymacTopFragment extends Fragment implements View.OnClickListener {
    /*ListView所需*/
    private RadioButton radioButton1, radioButton2, radioButton3;
    private RadioGroup radioGroup;
    private List<Map<String, Object>> typeMachine;
    private ListView nlistView;
    private nListViewAdapter nlistViewAdapter;
    private int flag = 0;


    private Button button;
    private View view;

    public MymacTopFragment() {
        // Required empty public constructor
    }

    public void repare_add_machine() {
        radioButton1 = (RadioButton) view.findViewById(R.id.radio_machine_door);
        radioButton2 = (RadioButton) view.findViewById(R.id.radio_machine_room);
        radioButton3 = (RadioButton) view.findViewById(R.id.radio_machine_center);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_radiogroup);
      /*  radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                //获取变更后的选中项的ID
            //    int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
              //  RadioButton rb = (RadioButton)getActivity().findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                Toast.makeText(getActivity(),"test",Toast.LENGTH_LONG);
            }
        });*/
    }

    // @Override
    public void testonCheckedChanged(RadioGroup group, int checkedId) {
        System.out.println(checkedId);
        switch (checkedId) {
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_add:
                alertDialog_addmac();
                break;
            default:
                break;
        }
    }

    public class nListViewAdapter extends BaseAdapter {
        private Context context;//运行上下文
        private List<Map<String, Object>> listItems;    //列表视图信息集合
        private LayoutInflater listContainer;//视图容器
        private boolean[] hasChecked;//被点击的标记变量

        public final class ListItemView {    //自定义控件集合
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
            ListItemView listItemView = null;
            if (convertView == null) {
                listItemView = new ListItemView();
                //获取list_item布局文件的视图
                convertView = listContainer.inflate(R.layout.add_machine_layout, null);
                //获取控件对象
                listItemView.fragment_addmachine_img = (ImageView) convertView.findViewById(R.id.fragment_addmachine_img);
                listItemView.fragment_addmachine_typeid = (TextView) convertView.findViewById(R.id.fragment_addmachine_typeid);
                //设置控件集到convertView
                convertView.setTag(listItemView);
            } else {
                listItemView = (ListItemView) convertView.getTag();
            }

            //设置文字和图片
            listItemView.fragment_addmachine_img.setBackgroundResource((Integer) listItems.get(
                    position).get("fragment_addmachine_img"));
            listItemView.fragment_addmachine_typeid.setText((String) listItems.get(position)
                    .get("fragment_addmachine_typeid"));
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mymac_top, container, false);
        button = (Button) view.findViewById(R.id.button_add);
        button.setOnClickListener(this);
        return view;
    }

    public void alertDialog_addmac() {
        Toast.makeText(getActivity(), "alert dialog used to add machine", Toast.LENGTH_LONG).show();
        System.out.println("alert dialog used to add machine");
        Intent intent=new Intent(getActivity(), AddMachineActivity.class);
        startActivity(intent);
        /*2015.11.03 21:40:45*/
        /*
        * AlertDialog类setView方法加载布局，不够完善，无法实现OnCreateView方法*/
        /*创建对话框的构建器*/
       /* AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("添加设备");*/
        //builder.setView(R.layout.add_machine_layout);//5.0系统支持
      /*  View view=getActivity().getLayoutInflater().inflate(R.layout.add_machine_layout,null);
        builder.setView(view);//此处只加载布局，不onCrete它的fragment*/
      /*  builder.setIcon(R.mipmap.ic_launcher_logo);
        String[] items={};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                }
                }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "添加成功", Toast.LENGTH_LONG);
                        dialog.dismiss();
                    }
                });
                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
        /*设置对话框*/
        // builder.show();*/

    }
}

