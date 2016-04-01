package com.blenmaterial.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blenmaterial.Beans.ContactsBean;
import com.blenmaterial.Explosion.ExplosionField;
import com.blenmaterial.Explosion.factory.FlyawayFactory;
import com.blenmaterial.Layout.SwipeLayout;
import com.blenmaterial.R;
import com.blenmaterial.Utils.LogUtils;

import java.util.ArrayList;


/**
 * Created by Blensmile on 2016/3/20.
 */
public class ContactsRcAdapter extends RecyclerView.Adapter<ContactsRcAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ContactsBean> list;
    private static SwipeLayout openedItems;//限制只能一个打开

    /**
     * 构造方法
     *
     * @param context
     * @param list
     */
    public ContactsRcAdapter(Context context, ArrayList<ContactsBean> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * 创建ViewHolder的rootView
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SwipeLayout view = (SwipeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacts, parent, false);
        return new ViewHolder(view);
    }


    /**
     * 设置View,这里可以把onclick写出去,不用这样写了,以后再说
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //添加listener,保证只能同时打开一个.执行顺序为 A onStartOpen-->A onOpen-->
        holder.rootView.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                LogUtils.i(position + "onClose");
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                openedItems = layout;
                LogUtils.i(position + "onOpen");
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                if (null != openedItems) {
                    //                    openedItems.close(true);
                    //                    openedItems = null;
                    closeAllItems();
                }
                LogUtils.i(position + "onStartOpen");
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

                LogUtils.i(position + "onStartClose");
            }
        });
        holder.head.setImageResource(list.get(position).icon);
        holder.name.setText(list.get(position).name);
        int pos = list.get(position).message;
        holder.point.setVisibility(pos>0 ? View.VISIBLE:View.INVISIBLE);

        if(pos>0)
            holder.point.setText(String.valueOf(pos));//here, if set a int value is not correct

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExplosionField explosionField = new ExplosionField(context, new FlyawayFactory());
                //explosionField.addListener(view);
                //getParent返回的ViewParent可能不是View,强转可能会有问题
                //explosionField.explode((View) v.getParent().getParent());
                //((SwipeLayout) v.getParent().getParent()).close(true);//全部复位

                //这样就不用那么麻烦去找父控件了
                openedItems.close(false);//不用动画效果
                explosionField.explode(openedItems);
                openedItems = null;//置空

                //监听结束
                explosionField.setOnExplosionAnimationEnd(new ExplosionField.OnExplosionAnimationEnd() {
                    @Override
                    public void onExplosionAnimationEnd() {
                        //mCRAdapter.//remove(list.get(position));
                        list.remove(position);
                        //notifyItemChanged(position);
                        notifyDataSetChanged();//删除元素的逻辑问题解决了
                        //setItemAnimator(new DefaultItemAnimator());//添加动画

                    }
                });
            }
        });
        //        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.space);
        //        mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /** used to close item
     *
     */
    public void closeAllItems() {
        if(null != openedItems) {
            openedItems.close(true);
            openedItems = null;//置空
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public SwipeLayout rootView;
        public ImageView head;
        public TextView name;
        public TextView delete;
        public TextView point;

        public ViewHolder(SwipeLayout view) {
            super(view);
            rootView = view;
            head = (ImageView) view.findViewById(R.id.head_iv);
            name = (TextView) view.findViewById(R.id.name_tv);
            delete = (TextView) view.findViewById(R.id.tv_delete);
            point = (TextView) view.findViewById(R.id.point_tv);
        }

    }

}
