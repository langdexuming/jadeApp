package com.example.langdexuming.jadeapp.fragment.content;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.langdexuming.jadeapp.MainActivity;
import com.example.langdexuming.jadeapp.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndexProductFragment extends Fragment implements ViewPager.OnPageChangeListener{

    /*ViewPager相关对象*/
    private ViewPager viewPager_product;//内部
    private ImageView[] imageViews;//指示器图片
    private static ArrayList<View> index_product_views=new ArrayList<>();//添加View时，需使用activity的getLayoutInflater()方法，此处设置静态变量，共享mainactivity的Arraylist变量
    private int currentIndex;



    public IndexProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_index_product,container,false);
        // Inflate the layout for this fragment
        System.out.println("had already load fragment_index_product!");
        init(view);
        return view;
    }
    /*初始化单个页面*/
    public void init(View view) {
        /*加载viewpager所需*/
        index_product_views= MainActivity.index_product_views;
        viewPager_product=(ViewPager)view.findViewById(R.id.viewpager_product_show);
        viewPager_product.setAdapter(new ProductPageAdapter());
        viewPager_product.setCurrentItem(0);//首页
        viewPager_product.setOnPageChangeListener(this);
        initPoint(view);

    }
    private void initPoint(View view){
        LinearLayout pointLayout=(LinearLayout)view.findViewById(R.id.point_layout);
        /*生成imageViews*/
        imageViews=new ImageView[index_product_views.size()];
        for(int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView) pointLayout.getChildAt(i);
        }
        currentIndex=0;
        imageViews[currentIndex].setImageResource(R.mipmap.ic_circle_selected);
    }
    private void setCurrentPoint(int position){
        if(currentIndex<0||currentIndex==position||currentIndex>imageViews.length-1){
            return;
        }
        System.out.println("setCurrentIndex ing");
        imageViews[currentIndex].setImageResource(R.mipmap.ic_circle_deafult);
        imageViews[position].setImageResource(R.mipmap.ic_circle_selected);
        currentIndex=position;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        System.out.println("page:"+position+"selected!");
        setCurrentPoint(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /*内部viewpager适配器*/
    class ProductPageAdapter extends PagerAdapter{
        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }

        @Override
        public int getCount() {
            return index_product_views.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView(index_product_views.get(position));
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v=index_product_views.get(position);
            container.addView(v);
            return v;
        }
    }

}
