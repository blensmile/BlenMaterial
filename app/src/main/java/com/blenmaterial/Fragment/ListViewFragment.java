package com.blenmaterial.Fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.blenmaterial.MyApplication;
import com.blenmaterial.R;
import com.blenmaterial.Utils.Cheeses;
import com.blenmaterial.Utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by Blensmile on 2016/3/18.
 */
public class ListViewFragment extends Fragment {

    private SwipeRefreshLayout mSwipe;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化集合,listview的item
        LogUtils.i("Fragment 1 onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> list = new ArrayList();
        for(int i = 0;i< Cheeses.NAMES.length;i++) {
            list.add(Cheeses.NAMES[i]);
        }
        mSwipe = (SwipeRefreshLayout) inflater.inflate(R.layout.list_swiperefresh,container,false);
        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                tv.setText("正在刷新");
                Toast.makeText(getActivity(),"正在刷新",Toast.LENGTH_SHORT).show();
                // TODO Auto-generated method stub
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
//                        tv.setText("刷新完成");
                        mSwipe.setRefreshing(false);
                    }
                }, 6000);
            }
        });
//        ListView listView = new ListView(MyApplication.getContext());//以前创建的..
        ListView listView = (ListView) mSwipe.findViewById(R.id.list_swipe);

        ArrayAdapter adapter = new ArrayAdapter(MyApplication.getContext(),android.R.layout.test_list_item,list);
        listView.setAdapter(adapter);

        LogUtils.i("Fragment 1 onCreateView");
        return mSwipe;
    }
}
