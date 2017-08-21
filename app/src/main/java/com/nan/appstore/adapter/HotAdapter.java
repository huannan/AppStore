package com.nan.appstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.nan.appstore.model.HotBean;

import java.util.List;

/**
 * @author fyales
 * @since 8/26/15.
 */
public class HotAdapter extends BaseAdapter {

    private Context mContext;
    private List<HotBean> mList;

    public HotAdapter(Context context, List<HotBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(com.fyales.tagcloud.library.R.layout.tagview, null);
            holder = new ViewHolder();
            holder.tagBtn = (Button) convertView.findViewById(com.fyales.tagcloud.library.R.id.tag_btn);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        final String text = getItem(position);
        holder.tagBtn.setText(text);
        return convertView;
    }

    static class ViewHolder {
        Button tagBtn;
    }
}
