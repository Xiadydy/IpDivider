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
    private Context mContext;
    private Map<Integer, ViewHolder> viewHolderMap;
    //lvdi start
    private List<SubNetInfomationBeanDto> params;

    public SubnetSettingAdapter(final Context mContext, final List<SubNetInfomationBeanDto> params) {
        this.viewHolderMap = new HashMap<>();
        this.mContext = mContext;
        this.params = params;
    }

    @Override
    public int getCount() {
        return this.params.size();
    }

    //获取用户填写参数
    public List<SubNetInfomationBeanDto> getData() {
        for (int size = this.params.size(), i = 0; i < size; ++i) {
            Log.i("getData", new StringBuilder(String.valueOf(i)).toString());
            final ViewHolder viewHolder = this.viewHolderMap.get(i);
            SubNetInfomationBeanDto dto;
            if (this.params.get(i) != null) {
                dto = this.params.get(i);
            }
            else {
                dto = new SubNetInfomationBeanDto();
            }
            final StringBuilder sb = new StringBuilder();
            if (viewHolder.SubnetName.getText() == null || viewHolder.SubnetName.getText().toString().trim().equals("")) {
                sb.append(viewHolder.SubnetOrder.getText().toString());
            }
            else {
                sb.append(viewHolder.SubnetName.getText().toString());
            }
            dto.setSubMaskName(sb.toString());
            if (!viewHolder.IPCount.getText().toString().equals("")) {
                dto.setNeedIpCount(Integer.parseInt(viewHolder.IPCount.getText().toString()));
            }
            dto.setSubNetId(i+1);
            if (!this.params.contains(dto)) {
                this.params.add(dto);
            }
        }
        return this.params;
    }

    @Override
    public Object getItem(final int n) {
        return null;
    }

    @Override
    public long getItemId(final int n) {
        return 0L;
    }

    @Override
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
