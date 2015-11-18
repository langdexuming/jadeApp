package com.example.langdexuming.jadeapp.fragment.top;


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
public class IndexTopFragment extends Fragment implements View.OnClickListener{
    private Button button;


    public IndexTopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_top, container, false);
        button=(Button)view.findViewById(R.id.button_more);
        button.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.button_more:
            Toast.makeText(getActivity(), "button_more", Toast.LENGTH_LONG).show();
            break;
    }

    }
}
