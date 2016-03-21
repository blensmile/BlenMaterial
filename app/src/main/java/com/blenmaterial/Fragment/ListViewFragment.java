package com.blenmaterial.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.blenmaterial.MyApplication;
import com.blenmaterial.Utils.Cheeses;
import com.blenmaterial.Utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by Blensmile on 2016/3/18.
 */
public class ListViewFragment extends Fragment {
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
        ListView listView = new ListView(MyApplication.getContext());
        ArrayAdapter adapter = new ArrayAdapter(MyApplication.getContext(),android.R.layout.test_list_item,list);
        listView.setAdapter(adapter);
        LogUtils.i("Fragment 1 onCreateView");
        return listView;
    }
}
