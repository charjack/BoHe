package com.charjack.bohe;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class GuideActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private GuidePagerAdapter gpAdapter;
    private ViewPager vp;
    private ArrayList<View> viewlist;
    private ImageView iv;
    private Button bt;

    //禁用ViewPager左右两侧拉到边界的渐变颜色
    private EdgeEffectCompat mRightEdge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_guide);
        vp = (ViewPager) findViewById(R.id.guide_pager);
        initdata();

    }

    private void initdata() {
        viewlist = new ArrayList<>();

        for(int i = 0;i<4;i++){
            View view = getLayoutInflater().inflate(R.layout.activity_guide_layout, null);
            ImageView img = (ImageView) view.findViewById(R.id.guide_img);
            if (i == 0) {
                img.setImageResource(R.mipmap.walkthrough_1);
            } else if (i == 1) {
                img.setImageResource(R.mipmap.walkthrough_2);
            } else if (i == 2) {
                img.setImageResource(R.mipmap.walkthrough_3);
            } else if (i == 3) {
                img.setImageResource(R.mipmap.walkthrough_4);
                Button startBtn = (Button) view.findViewById(R.id.start_btn);
                startBtn.setVisibility(View.VISIBLE);
                startBtn.setOnClickListener(this);
            }
            viewlist.add(view);
        }
        gpAdapter = new GuidePagerAdapter(viewlist);
        vp.setAdapter(gpAdapter);
        vp.addOnPageChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mRightEdge != null && !mRightEdge.isFinished()) {//到了最后一张并且还继续拖动，出现蓝色限制边条了
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
    }
}
