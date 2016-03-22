package com.blenmaterial.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.blenmaterial.Adapters.ContactsRcAdapter;
import com.blenmaterial.Beans.ContactsBean;
import com.blenmaterial.Layout.DividerItemDecoration;
import com.blenmaterial.R;
import com.blenmaterial.Utils.Cheeses;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Blensmile on 2016/3/19.
 */
public class ContactsFragment extends Fragment {
    private int[] ICON_IDs = {R.drawable.baidu_map, R.drawable.changba, R.drawable.gaode_map, R.drawable.iqiyi, R.drawable.jd, R.drawable.lm, R.drawable.qq, R.drawable.qq_music, R.drawable.qzone, R.drawable.tb, R.drawable.vx, R.drawable.wb};
    ArrayList<ContactsBean> list;
    private RecyclerView mRcView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        list = new ArrayList<>();
        Random random = new Random();
        for (String i : Cheeses.NAMES) {
            list.add(new ContactsBean(i, ICON_IDs[random.nextInt(ICON_IDs.length)]));
        }

        mRcView = new RecyclerView(getActivity());//一开始在想可不可代码创建,因为侧拉删除布局有问题,其实是可以的
        //        mRcView = (RecyclerView) inflater.inflate(R.layout.recicler_item, container, false);
        //        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //        mRcView.setLayoutParams(params);

        //这个是必须设置的,不然不能正常绑定
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayout.VERTICAL);
        mRcView.setLayoutManager(manager);
        mRcView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        ContactsRcAdapter mCRAdapter = new ContactsRcAdapter(getActivity(), list);
        mRcView.setAdapter(mCRAdapter);
        return mRcView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

}
