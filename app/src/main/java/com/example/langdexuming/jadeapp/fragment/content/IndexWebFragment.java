package com.example.langdexuming.jadeapp.fragment.content;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.langdexuming.jadeapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndexWebFragment extends Fragment {
    private WebView webView;


    public IndexWebFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_web, container, false);
        WebView_on(view);
        return view;
    }
    public void WebView_on(View v){
        webView =(WebView)v.findViewById(R.id.webview_index);
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        try {
            //设置打开的页面地址
           // webView.loadUrl("http://www.jade-elec.com/?fnews");
            webView.loadUrl("file:///android_asset/index.html");
            //webView.loadUrl("http://www.jade-elec.com/?fnews");
         //   webView.loadDataWithBaseURL("http://www.jade-elec.com/?fnews");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        webView.setWebViewClient(new WebViewClient());//无需打开浏览器访问，启用App内部客户端访问
    }


}
