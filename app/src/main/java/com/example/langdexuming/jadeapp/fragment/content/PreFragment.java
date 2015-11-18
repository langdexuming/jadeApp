package com.example.langdexuming.jadeapp.fragment.content;


import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.langdexuming.jadeapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preference);
    }
}
