package test.oubowu.com.badgeviewdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initTabLayoutAndViewPager();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("加角标Demo");
        setSupportActionBar(mToolbar);
    }

    List<String> mTitles;

    private void initTabLayoutAndViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        List<Fragment> fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add("时尚");
        mTitles.add("复古");
        mTitles.add("朋克");
        mTitles.add("摇滚");
        mTitles.add("嘻哈");
        mTitles.add("蓝调");
        mTitles.add("爵士");
        mTitles.add("古典");
        for (String title : mTitles) {
            fragments.add(SimpleFragment.newInstance(title));
        }
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, mTitles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTitles.size(); i++) {
            setUpTabBadge(i);
        }
    }

    /**
     * 设置Tablayout上的标题的角标
     */
    private void setUpTabBadge(int position) {
        TabLayout.Tab tab = mTabLayout.getTabAt(position);
        if (tab != null) {
            View view = LayoutInflater.from(this).inflate(R.layout.tab_title_layout, null);
            ((TextView) view.findViewById(R.id.tv_title)).setText(mTitles.get(position));
            tab.setCustomView(view);
            BadgeView badgeView = new BadgeView(this, (View) view.getParent());
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setText(position+"");
            badgeView.show(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setUpCartMenuItem(menu);
        return true;
    }

    private void setUpCartMenuItem(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_cart);
        View actionView = MenuItemCompat.getActionView(item);
        View mCartView = ((ViewGroup) actionView).getChildAt(0);
        if (mCartView != null) {
            BadgeView badgeView = new BadgeView(MainActivity.this, mCartView);
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setText("0");
            mCartView.measure(0, 0);
            badgeView.setBadgeMargin(mCartView.getMeasuredWidth() / 55, mCartView.getMeasuredWidth() / 65);
            badgeView.show(true);
            actionView.measure(0, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cart) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
