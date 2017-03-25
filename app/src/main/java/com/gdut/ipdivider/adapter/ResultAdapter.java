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
    private List<SubNetInfomationBean> result;
    private LayoutInflater layoutInflater;
    private Context mContext;

    public ResultAdapter(final Context mContext, final List<SubnetEntity> data, final List<SubNetInfomationBean> result) {
        this.mContext = mContext;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.result = result;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(final int n) {
        return this.data.get(n);
    }

    @Override
    public long getItemId(final int n) {
        return n;
    }

    @Override
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
        tag.name.setText(this.result.get(n).getSubMaskName());
        tag.segment.setText(this.result.get(n).getSubNetAdress());
        tag.ipRange.setText(this.result.get(n).getSubNetScope());
        tag.maskNum.setText(this.result.get(n).getMask());
        tag.ipCount.setText(String.valueOf(this.result.get(n).getNeedIpCount()));
        tag.noUseIPCount.setText(String.valueOf(this.result.get(n).getNotUseCount()));
        return inflate;
    }

    class ViewHolder
    {
        TextView ipCount; //ip数量
        TextView ipRange; //ip范围
        TextView maskNum;  //子网掩码
        TextView name;   //子网别名
        TextView noUseIPCount; //未使用的ip数量
        TextView segment; //子网范围以ip/mask显示
        TextView title; //子网id
    }
}
