package com.gdut.ipdivider.adapter;

import java.util.*;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.entity.*;
import android.content.*;
import android.view.*;
import android.widget.*;

public class ResultAdapter extends BaseAdapter
{
    private List<SubnetEntity> data;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ResultAdapter(final Context mContext, final List<SubnetEntity> data) {
        this.mContext = mContext;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return this.data.size();
    }

    public Object getItem(final int n) {
        return this.data.get(n);
    }

    public long getItemId(final int n) {
        return n;
    }

    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        ViewHolder tag;
        if (inflate == null) {
            tag = new ViewHolder();
            inflate = this.layoutInflater.inflate(R.layout.result_listview_item, null);
            tag.title = (TextView)inflate.findViewById(R.id.title);
            tag.name = (TextView)inflate.findViewById(R.id.name);
            tag.ipCount = (TextView)inflate.findViewById(R.id.ip_count);
            tag.segment = (TextView)inflate.findViewById(R.id.segment);
            tag.ipRange = (TextView)inflate.findViewById(R.id.ip_range);
            tag.maskNum = (TextView)inflate.findViewById(R.id.mask_num);
            tag.noUseIPCount = (TextView)inflate.findViewById(R.id.nouse_ip_count);
            inflate.setTag(tag);
        }
        else {
            tag = (ViewHolder)inflate.getTag();
        }
        tag.title.setText(this.mContext.getString(R.string.subnet) + (n + 1));
        tag.name.setText(this.data.get(n).getName());
        tag.segment.setText(this.data.get(n).getSegment());
        tag.ipRange.setText(this.data.get(n).getIpRange());
        tag.maskNum.setText(this.data.get(n).getMaskNum());
        tag.ipCount.setText(String.valueOf(this.data.get(n).getIpCount()));
        tag.noUseIPCount.setText(String.valueOf(this.data.get(n).getNoUseIPCount()));
        return inflate;
    }

    class ViewHolder
    {
        TextView ipCount;
        TextView ipRange;
        TextView maskNum;
        TextView name;
        TextView noUseIPCount;
        TextView segment;
        TextView title;
    }
}
