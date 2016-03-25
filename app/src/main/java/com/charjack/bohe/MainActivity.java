package com.charjack.bohe;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.charjack.bohe.fragment.CategoryFragment;
import com.charjack.bohe.fragment.ChooseTabFragment;
import com.charjack.bohe.fragment.HomeFragment;
import com.charjack.bohe.fragment.ProfileFragment;
import com.charjack.bohe.fragment.SelectFragment;
import com.charjack.bohe.utils.FragmentTabUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity implements FragmentTabUtils.OnRgsExtraCheckedChangedListener
,ChooseTabFragment.chooseTabFragmentListener {

    public SystemBarTintManager tintManager;
    private RadioGroup rgs;
    private List<Fragment> fragments = new ArrayList<Fragment>();
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);

        rgs = (RadioGroup) findViewById(R.id.radio_group);
        fragments.add(new HomeFragment());
        fragments.add(new SelectFragment());
        fragments.add(new CategoryFragment());
        fragments.add(new ProfileFragment());
        FragmentTabUtils utils = new FragmentTabUtils(getSupportFragmentManager(), fragments, R.id.fragment_main_container, rgs);
        utils.setOnRgsExtraCheckedChangedListener(this);

        //状态栏悬浮效果
        tintManager = new SystemBarTintManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (findViewById(R.id.root_view) != null) {
                findViewById(R.id.root_view).post(new Runnable() {   //让布局绘制完成了再设置
                    @Override
                    public void run() {
                        tintManager.setStatusBarTintEnabled(true);
                        tintManager.setStatusBarTintResource(R.color.green);
                        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
                        findViewById(R.id.root_view).setPadding(0, config.getPixelInsetTop(false), 0, config.getPixelInsetBottom());
                    }
                });
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_search:
                    startActivity(new Intent(MainActivity.this,SearchActivity.class));
                    break;
            }
            return true;
        }
    };

    public void onResume() {
        super.onResume();
    }
    public void onPause() {
        super.onPause();
    }

    @Override
    public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
        System.out.println("checkedid:"+checkedId);//系统分配的一个id值
        System.out.println("index:"+index);  //0--3
        System.out.println("checkedid:" + rgs.getChildAt(index).getId());
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    public void exit() {
        //v4包 的fragment 用getSupportFragmentManager
        if(getSupportFragmentManager().getBackStackEntryCount()==0){
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
//            System.exit(0);
            }
        }else{
            getSupportFragmentManager().popBackStack();//出栈
        }
    }

    @Override
    public void closefragment() {
        exit();
    }
}
