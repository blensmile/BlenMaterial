package com.blenmaterial;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.blenmaterial.Fragment.ContactsFragment;
import com.blenmaterial.Fragment.ListViewFragment;
import com.blenmaterial.Fragment.SimpleRcFragment;
import com.blenmaterial.Utils.LogUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView0;
    private ListView listView;
    private ViewPager mPager;
    private TabLayout mTab;
    private Toolbar mToolBar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private ArrayList<View> listV;
    private ArrayList<Fragment> listF;

    private String[] mTitle = new String[]{"blen", "this", "shit"};
    private NavigationView.OnNavigationItemSelectedListener mNavItemOnClick = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_blen:
                    mPager.setCurrentItem(0);
                    break;
                case R.id.menu_this:
                    mPager.setCurrentItem(1);
                    break;
                case R.id.menu_shit:
                    mPager.setCurrentItem(2);
                    break;
                default:
                    return false;
            }
            mDrawerLayout.closeDrawer(mNavigationView);
            return true;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) this.findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(mNavItemOnClick);


        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        //mToolBar.setLogo(R.mipmap.ic_launcher);    //set logo here. of no use
        mToolBar.setTitle("Blen");
        mToolBar.setSubtitle("Hello Blen");
        setSupportActionBar(mToolBar);
        //these all must be set after setSupportActionBar
        mToolBar.setNavigationIcon(R.drawable.google_home);
        //mToolBar.setOnMenuItemClickListener(mOnMenuClick);//there is already a function to handle onclick event
        //this is a separated click event
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(mNavigationView);
            }
        });

        //        ActionBar actionBar = this.getSupportActionBar();
        //        if(null != actionBar) {                         //in case of nullpionterException, if actionBar is not exist
        //            actionBar.setDisplayHomeAsUpEnabled(true);  //enable showing my icon
        //            actionBar.setHomeAsUpIndicator(R.drawable.google_home);
        //        }


        mPager = (ViewPager) findViewById(R.id.viewpager);
        mTab = (TabLayout) findViewById(R.id.tabs);
        initFragementPager();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("cancle", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
            }
        });


        //        initView();
        //

        //        File file = new File(Environment.getExternalStorageDirectory().getPath(),"t1.txt");
        //        try {
        //            FileWriter writer = new FileWriter(file);
        //            writer.write("hello world");
        //            writer.close();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        LogUtils.i("MainActivity onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("MainActivity onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i("MainActivity onStart");
    }

    @Override
    public void onResume() {
        //        isForeground = true;
        super.onResume();
        LogUtils.i("MainActivity onResume");
    }

    @Override
    public void onPause() {
        //        isForeground = false;
        super.onPause();
        LogUtils.i("MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i("MainActivity onStop");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        LogUtils.i("MainActivity onDestroy");
    }

    //this turns out to be of no use
    private Toolbar.OnMenuItemClickListener mOnMenuClick = new Toolbar.OnMenuItemClickListener() {

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {

                case R.id.icon_blen:
                    Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                    break;
                //TODO other setting events are to be done
                case R.id.action_blen:
                    mPager.setCurrentItem(0, true);
                    break;
                case R.id.action_this:
                    mPager.setCurrentItem(1, true);
                    break;
                case R.id.action_shit:
                    mPager.setCurrentItem(2, true);
                    break;
                default:
                    return false;
            }
            return true;
        }
    };

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

        //noinspection SimplifiableIfStatement
        //TODO other setting events are to be done
        switch (item.getItemId()) {
            case R.id.icon_blen:
                Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(getApplicationContext(), "Setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_blen:
                mPager.setCurrentItem(0, true);
                break;
            case R.id.action_this:
                mPager.setCurrentItem(1, true);
                break;
            case R.id.action_shit:
                mPager.setCurrentItem(2, true);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;

    }

    private void initFragementPager() {
        listF = new ArrayList<>();
        listF.add(new ContactsFragment());
        listF.add(new ListViewFragment());
        listF.add(new SimpleRcFragment());


        //初始化pagerAdapter控件,这里每一页都是listview作为内容

        FragmentPagerAdapter fpAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listF.get(position);
            }

            @Override
            public int getCount() {
                return listF.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle[position];
            }

        };
        mPager.setAdapter(fpAdapter);


        //将TabLayout与pager和adapter绑定

        mTab.addTab(mTab.newTab().setText(mTitle[0]));
        mTab.setupWithViewPager(mPager);
        mTab.setTabsFromPagerAdapter(fpAdapter);

    }
}
