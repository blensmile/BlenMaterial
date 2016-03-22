package com.blenmaterial;

import android.content.pm.ActivityInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

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
    private ArrayList<View> listV;
    private ArrayList<Fragment> listF;

    private String[] mTitle = new String[]{ "blen", "this", "shit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBar actionBar = getSupportActionBar();
        BitmapFactory factory = new BitmapFactory();
        //        factory.decodeResource(R.drawable.google_plus_home_257px_554,);
        actionBar.setHomeAsUpIndicator(R.mipmap.ic_home);

        //        actionBar.setHomeAsUpIndicator(android.R.drawable.ic_dialog_alert);

        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mTab = (TabLayout) findViewById(R.id.tabs);
        initFragementPager();
        //        initViewPage();



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

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i("MainActivity onRestart");
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
