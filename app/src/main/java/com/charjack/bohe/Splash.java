package com.charjack.bohe;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.charjack.bohe.utils.BaseUtils;
import com.charjack.bohe.utils.MyActivityManager;


public class Splash extends BaseActivity {

    private RelativeLayout splashlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置启动页面全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        splashlayout = (RelativeLayout) findViewById(R.id.splish_layout);
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f,1.0f);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(3*1000);//设置开启时间
        //如果当前APP处于开启状态，不显示开启动画
        if (MyActivityManager.getActivityManager().getActivity(MainActivity.class) != null) {
            startAction();
        } else {
            splashlayout.startAnimation(animationSet);
        }
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startAction();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    private void startAction() {
        Intent intent = new Intent();
        if (BaseUtils.isFirst(this)) {
            intent.setClass(this, GuideActivity.class);
//            Toast.makeText(this,"guide",Toast.LENGTH_SHORT).show();
        } else {
            intent.setClass(this, MainActivity.class);
//            Toast.makeText(this,"main",Toast.LENGTH_SHORT).show();
        }
//        intent.setClass(this, GuideActivity.class);
        startActivity(intent);
        this.finish();
    }

}
