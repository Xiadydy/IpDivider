package com.gdut.ipdivider.adapter;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.entity.*;
import android.content.*;
import java.util.*;
import android.util.*;
import android.view.*;
import android.widget.*;

public class SubnetSettingAdapter extends BaseAdapter
{
    private List<SubnetEntity> data;
    private Context mContext;
    private Map<Integer, ViewHolder> viewHolderMap;

    public SubnetSettingAdapter(final Context mContext, final List<SubnetEntity> data) {
        this.viewHolderMap = new HashMap<>();
        this.mContext = mContext;
        this.data = data;
    }

    public int getCount() {
        return this.data.size();
    }

    public List<SubnetEntity> getData() {
        for (int size = this.data.size(), i = 0; i < size; ++i) {
            Log.i("getData", new StringBuilder(String.valueOf(i)).toString());
            final ViewHolder viewHolder = this.viewHolderMap.get(i);
            SubnetEntity subnetEntity;
            if (this.data.get(i) != null) {
                subnetEntity = this.data.get(i);
            }
            else {
                subnetEntity = new SubnetEntity();
            }
            final StringBuilder sb = new StringBuilder();
            new StringBuilder();
            if (viewHolder.SubnetName.getText() == null || viewHolder.SubnetName.getText().toString().trim().equals("")) {
                sb.append(viewHolder.SubnetOrder.getText().toString());
            }
            else {
                sb.append(viewHolder.SubnetName.getText().toString());
            }
            subnetEntity.setName(sb.toString());
            if (!viewHolder.IPCount.getText().toString().equals("")) {
                subnetEntity.setIpCount(Integer.parseInt(viewHolder.IPCount.getText().toString()));
            }
            if (!this.data.contains(subnetEntity)) {
                this.data.add(subnetEntity);
            }
        }
        return this.data;
    }

    public Object getItem(final int n) {
        return null;
    }

    public long getItemId(final int n) {
        return 0L;
    }

    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (this.viewHolderMap.get(n) == null) {
            viewHolder = new ViewHolder();
            viewHolder.mView = LayoutInflater.from(this.mContext).inflate(R.layout.subnet_setting_item, null);
            viewHolder.SubnetName = (EditText)viewHolder.mView.findViewById(R.id.subnet_name);
            viewHolder.IPCount = (EditText)viewHolder.mView.findViewById(R.id.ip_count);
            viewHolder.SubnetOrder = (TextView)viewHolder.mView.findViewById(R.id.subnet_order);
            this.viewHolderMap.put(n, viewHolder);
        }
        else {
            viewHolder = this.viewHolderMap.get(n);
        }
        viewHolder.SubnetOrder.setText(mContext.getString(R.string.subnet) + (n + 1));
        return viewHolder.mView;
    }

    class ViewHolder
    {
        EditText IPCount;
        EditText SubnetName;
        TextView SubnetOrder;
        View mView;
    }
}
