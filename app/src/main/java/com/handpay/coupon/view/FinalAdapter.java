package com.handpay.coupon.view;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FinalAdapter<T> extends BaseAdapter {

    private List<T> mShowItems = new ArrayList<>();
    private List<String> mHomeTagList = new ArrayList<>();
    private int mLayoutId = 0;
    private OnAdapterListener mAdapterListener;

    public FinalAdapter(List<T> showItems, List<String> homeTagList, int layoutId, OnAdapterListener adapterListener) {
        mShowItems = showItems;
        mHomeTagList = homeTagList;
        this.mLayoutId = layoutId;
        this.mAdapterListener = adapterListener;
    }
    @Override
    public int getCount() {
        return mShowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mShowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FinalViewHolder finalViewHolder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), mLayoutId, null);
            finalViewHolder = new FinalViewHolder(convertView);

            convertView.setTag(finalViewHolder);

        } else {
            finalViewHolder = (FinalViewHolder) convertView.getTag();
        }
        bindView(finalViewHolder, mShowItems.get(position),mHomeTagList.get(position),position);
        return convertView;
    }
    private void bindView( FinalViewHolder finalViewHolder,T content,String tag,int position) {
        mAdapterListener.bindView(finalViewHolder,content,tag,position);
    }
    //接口
    public interface OnAdapterListener<T>
    {
        void bindView(FinalViewHolder finalViewHolder, T content, String tag, int position);
    }

    //接收

    public static class FinalViewHolder{

        public View mLayoutView;

        public FinalViewHolder(View layoutView) {
            //tv = (TextView) view.findViewById(R.id.tv_home_title);
            this.mLayoutView = layoutView;
        }

        //根据id自动查找控件
        // private HashMap<Integer, View> mViewHashMap = new HashMap<>();

        //使用性能更高的
        private SparseArray<View> mViewHashMap = new SparseArray<>();

        public View getViewById(int id) {
            View view = mViewHashMap.get(id);
            if (view == null) {
                view = mLayoutView.findViewById(id);
                mViewHashMap.put(id, view);
            }

            return view;
        }

        //以后可以增加
        public TextView getTextView(int id) {
            return (TextView) getViewById(id);
        }

    }
}
