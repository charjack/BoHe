package com.charjack.bohe;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class SearchActivity extends BaseActivity {
    public SystemBarTintManager tintManager;
    public SearchView searchView;
    public String searchWords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_holo_dark);//设置ToolBar头部图标

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //状态栏悬浮效果
        tintManager = new SystemBarTintManager(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (findViewById(R.id.searchrootview) != null) {
                findViewById(R.id.searchrootview).post(new Runnable() {   //让布局绘制完成了再设置
                    @Override
                    public void run() {
                        tintManager.setStatusBarTintEnabled(true);
                        tintManager.setStatusBarTintResource(R.color.green);
                        SystemBarTintManager.SystemBarConfig config = tintManager.getConfig();
                        findViewById(R.id.searchrootview).setPadding(0, config.getPixelInsetTop(false), 0, config.getPixelInsetBottom());
                    }
                });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        searchView = (SearchView)menu.findItem(R.id.action_search_textview).getActionView();//在菜单中找到对应控件的item
        searchView.setQueryHint("搜索家居");
        searchView.setFocusable(true);
        searchView.setMaxWidth(550);
        searchView.onActionViewExpanded();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_search_button:
                    searchView.clearFocus();//移除searchview的焦点，同时关闭虚拟键盘
                    searchWords = (String) searchView.getQuery();//获取搜索的关键词
                    break;
            }
            return true;
        }
    };
}
