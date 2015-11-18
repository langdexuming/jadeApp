package com.example.langdexuming.jadeapp.fragment.top;


import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.langdexuming.jadeapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindTopFragment extends Fragment implements View.OnClickListener{
    private TopRefreshListener topRefreshListener;

    private Button button;
    private View view;
    public FindTopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_find_top,container,false);
        button=(Button) view.findViewById(R.id.button_refresh);
        button.setOnClickListener(this);
        System.out.println("test builder");
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_refresh:
                topRefreshListener.changeListView();
                Toast.makeText(getActivity(),"button_refresh",Toast.LENGTH_LONG).show();
                break;
        }
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        topRefreshListener=(TopRefreshListener)activity;
    }
    /*定义一个刷新的接口*/
    public static interface TopRefreshListener{
        public void changeListView();
    }
}
