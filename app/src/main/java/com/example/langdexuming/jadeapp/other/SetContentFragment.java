package com.example.langdexuming.jadeapp.other;


import android.os.Bundle;
import android.app.Fragment;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.langdexuming.jadeapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SetContentFragment extends Fragment {

    public SetContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_set_content,container,false);
    }
}
