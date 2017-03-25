package com.gdut.ipdivider.adapter;

import android.content.*;
import java.util.*;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.entity.*;
import android.view.*;
import android.widget.*;

public class ProjectListAdapter extends BaseAdapter
{
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Project> mList;

    public ProjectListAdapter(final Context mContext, final List<Project> mList) {
        this.mContext = mContext;
        this.mList = mList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return this.mList.size();
    }

    public Project getItem(final int n) {
        return this.mList.get(n);
    }

    public long getItemId(final int n) {
        return n;
    }

    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ViewHolder tag;
        if (view == null) {
            tag = new ViewHolder();
            inflate = this.mInflater.inflate(R.layout.project_listview_item, null);
            tag.date = (TextView)inflate.findViewById(R.id.date);
            tag.name = (TextView)inflate.findViewById(R.id.project_name);
            tag.order = (TextView)inflate.findViewById(R.id.order);
            inflate.setTag(tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        tag.date.setText(this.mList.get(n).getTime());
        tag.name.setText(this.mList.get(n).getProjectName());
        tag.order.setText(String.valueOf(n + 1));
        return inflate;
    }

    class ViewHolder
    {
        TextView date;
        TextView name;
        TextView order;
    }
}
