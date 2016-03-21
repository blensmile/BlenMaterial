package com.blenmaterial.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blenmaterial.Adapters.SimpleRcContactsAdapter;
import com.blenmaterial.MyApplication;
import com.blenmaterial.Utils.Cheeses;
import com.blenmaterial.Utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by Blensmile on 2016/3/20.
 */
public class SimpleRcFragment extends Fragment {

    private ArrayList<String> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       list = new ArrayList();
        for(int i = 0;i< Cheeses.NAMES.length;i++) {
            list.add(Cheeses.NAMES[i]);
        }
        RecyclerView listView = new RecyclerView(MyApplication.getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity() );//没有LayoutManager就没法显示诶
        manager.setOrientation(LinearLayout.VERTICAL);
        listView.setLayoutManager(manager);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(params);

        SimpleRcContactsAdapter adapter = new SimpleRcContactsAdapter(getActivity(),list);//,android.R.layout.test_list_item,list
        listView.setAdapter(adapter);
        LogUtils.i("Fragment 1 onCreateView");
        return listView;


    }


}
