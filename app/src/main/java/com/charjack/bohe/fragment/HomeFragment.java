package com.charjack.bohe.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.charjack.bohe.MainActivity;
import com.charjack.bohe.R;
import com.charjack.bohe.utils.BaseUtils;
import com.shizhefei.fragment.LazyFragment;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends LazyFragment {
    private IndicatorViewPager indicatorViewPager;
    private LayoutInflater inflate;
    private MainActivity mainActivity;
    public ArrayList<String> tabnames;
    public ImageView iv;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) context;
    }
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_home, container, false);
        setContentView(R.layout.fragment_home);
        iv = (ImageView) findViewById(R.id.changeItemView);

        ViewPager viewPager = (ViewPager) findViewById(R.id.fragment_tabmain_viewPager);
        Indicator indicator = (Indicator) findViewById(R.id.fragment_tabmain_indicator);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), 0xff00ced1, 5));

        float unSelectSize = 16;
        float selectSize = unSelectSize * 1.2f;
        int selectColor = getResources().getColor(R.color.timeColor);
        int unSelectColor = getResources().getColor(R.color.timeColor);
        indicator.setOnTransitionListener(new OnTransitionTextListener().setColor(selectColor, unSelectColor).setSize(selectSize, unSelectSize));
        viewPager.setOffscreenPageLimit(6);

        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        inflate = LayoutInflater.from(getApplicationContext());

        String[] tabnameary =BaseUtils.getTablelist(mainActivity);
        tabnames=new ArrayList<String>();
        if (tabnameary != null && tabnameary.length > 0) {
            for (String value : tabnameary) {
                tabnames.add(value);
            }
        }
//        tabnames.add("精选");
//        tabnames.add("卧室");
//        tabnames.add("书房");
//        tabnames.add("卫浴");
//        tabnames.add("客厅");
//        tabnames.add("厨房");
        // 注意这里 的FragmentManager 是 getChildFragmentManager(); 因为是在Fragment里面
        // 而在activity里面用FragmentManager 是 getSupportFragmentManager()
        indicatorViewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mainActivity,"click changeItemViewClick",Toast.LENGTH_SHORT).show();
                ChooseTabFragment p = ChooseTabFragment.getInstance("one");
                p.setChooseTabListener(mainActivity);
                FragmentTransaction ft =  getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_main_container, p);
                //把当前Fragment添加到Activity栈
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    @Override
    protected void onResumeLazy() {
        super.onResumeLazy();
        Log.d("cccc", "Fragment所在的Activity onResume, onResumeLazy " + this);
    }

    @Override
    protected void onFragmentStartLazy() {
        super.onFragmentStartLazy();
        Log.d("cccc", "Fragment 显示 " + this);
    }

    @Override
    protected void onFragmentStopLazy() {
        super.onFragmentStopLazy();
        Log.d("cccc", "Fragment 掩藏 " + this);
    }

    @Override
    protected void onPauseLazy() {
        super.onPauseLazy();
        Log.d("cccc", "Fragment所在的Activity onPause, onPauseLazy " + this);
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        Log.d("cccc", "Fragment View将被销毁 " + this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("cccc", "Fragment 所在的Activity onDestroy " + this);
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

        public MyAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        @Override
        public int getCount() {
            return tabnames.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflate.inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(tabnames.get(position));
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            SecondLayerFragment mainFragment = new SecondLayerFragment();
            Bundle bundle = new Bundle();
            bundle.putString(SecondLayerFragment.INTENT_STRING_TABNAME, tabnames.get(position));
            bundle.putInt(SecondLayerFragment.INTENT_INT_POSITION, position);
            mainFragment.setArguments(bundle);
            return mainFragment;
        }
    }



}
