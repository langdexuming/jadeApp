package com.example.langdexuming.jadeapp.data_class;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.langdexuming.jadeapp.fragment.content.AddMachineFragment;
import com.example.langdexuming.jadeapp.fragment.content.FindContentFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by langdexuming on 2015/11/3 00:24
 * Email:langdexuming_x@163.com.
 */
public class ItemMap{
    private Machine machine;
    private Map<String,Object> map;

    public ItemMap(MyListItemView myListItemView,Machine machine){
      //  map=new HashMap<String, Object>();
       // map.put(myListItemView.fragment_mymac_content_img.toString(),machine.name);
    }
    public final class MyListItemView {
   /*     private ImageButton fragment_mymac_content_img;
        private TextView fragment_mymac_content_name;
        private TextView fragment_mymac_content_loac;
        private LinearLayout fragment_mymac_content_linear;*/
    };

}
