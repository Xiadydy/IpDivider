package com.gdut.ipdivider.activity;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.adapter.*;

import android.text.TextUtils;
import android.view.*;

import com.gdut.ipdivider.tool.*;
import com.gdut.ipdivider.view.*;
import com.gdut.ipdivider.application.*;

import android.content.*;

import java.util.*;

import android.widget.*;
import android.os.*;
import android.util.*;

import static android.view.View.*;

public class MainActivity extends BaseActivity implements OnClickListener {
    private ImageButton btnAdd;
    private ImageButton btnCalculate;
    private ImageButton btnConfirm;
    private ImageButton btnProjrctList;
    private Context mContext;
    private IpEditText[] resIp;
    private IpEditText resMask;
    private LinearLayout stepsDeclare;
    private TextView subNetMaskNum;
    private IpEditText subNetNum;
    private int subnetCount;
    private ListView subnetSetting;
    private SubnetSettingAdapter subnetSettingAdapter;
    private List<SubNetInfomationBeanDto> receiveParam;
    private List<SubNetInfomationBeanDto> params;
    private double sumIPOffer;
    private int sumIPneed;

    public MainActivity() {
        this.resIp = new IpEditText[4];
    }

    private void AdjustListView2Full(final ListView listView) {
        final int count = listView.getAdapter().getCount();
        int n = 0;
        for (int i = count; i > 0; --i) {
            final View view = listView.getAdapter().getView(i - 1, null, null);
            view.measure(0, 0);
            n += view.getMeasuredHeight();
        }
        final int dividerHeight = listView.getDividerHeight();
        final ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = n + dividerHeight * (count - 1);
        listView.setLayoutParams(layoutParams);
    }

    private Boolean checkInputAndSetting() {
        if(TextUtils.isEmpty(this.resMask.getText())){
            SuperToast.makeText(this.mContext, this.getString(R.string.error),  "掩码尚未完成填写", 1000).showInCenter();
            return false;
        }
        this.receiveParam = ((SubnetSettingAdapter) this.subnetSetting.getAdapter()).getData();
        this.sumIPneed = 0;
        this.sumIPOffer = IPv4Util.getAllIpCount(getIPSrc(), Integer.parseInt(this.resMask.getText().toString()));
        int n = 1;
        for (final SubNetInfomationBeanDto dto: this.receiveParam) {
            if(dto.getNeedIpCount() == null || dto.getSubMaskName() == null){
                SuperToast.makeText(this.mContext, this.getString(R.string.error), String.valueOf(this.getString(R.string.subnet)) + n + "尚未完成所需字段填写", 1000).showInCenter();
                return false;
            }
            final int ipCount = dto.getNeedIpCount();
            if (ipCount == 0) {
                SuperToast.makeText(this.mContext, this.getString(R.string.error), String.valueOf(this.getString(R.string.subnet)) + n + this.getString(R.string.required_IP_number_can_not_be_0), 1000).showInCenter();
                return false;
            }
            this.sumIPneed += ipCount;
            ++n;
        }
        if (this.sumIPneed > this.sumIPOffer) {
            SuperToast.makeText(this.mContext, this.getString(R.string.warning), this.getString(R.string.IP_not_enough) + "差"+ (this.sumIPOffer-this.sumIPneed )+ "个ip", 1000).showInCenter();
            return false;
        }
        return true;
    }


    //修改IP拼接
    private String getIPSrc() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            if(TextUtils.isEmpty(this.resIp[i].getText())){
                SuperToast.makeText(this.mContext, this.getString(R.string.error), "网段地址没有配置,请重新配置", 1000).showInCenter();
                return null;
            }
            sb.append(String.valueOf(this.resIp[i].getText().toString().trim()) + ".");
        }
        final String string = sb.toString();
        final String substring = string.substring(0, string.length() - 1);
        System.out.println("getIPSrc = " + substring);
        return substring;
    }


    private void initView() {
        (this.subNetNum = (IpEditText) this.findViewById(R.id.subnet_count)).setType(IpEditText.TYPE.NETSUBNUM);
        this.stepsDeclare = (LinearLayout) this.findViewById(R.id.steps_declare);
        this.subnetSetting = (ListView) this.findViewById(R.id.subnet_setting_listview);
        this.resIp[0] = (IpEditText) this.findViewById(R.id.res_ip_1);
        this.resIp[1] = (IpEditText) this.findViewById(R.id.res_ip_2);
        this.resIp[2] = (IpEditText) this.findViewById(R.id.res_ip_3);
        this.resIp[3] = (IpEditText) this.findViewById(R.id.res_ip_4);
        final IpEditText[] resIp = this.resIp;
        for (int length = resIp.length, i = 0; i < length; ++i) {
            resIp[i].setType(IpEditText.TYPE.IP);
        }
        this.resMask = (IpEditText) this.findViewById(R.id.res_mask);
        (this.btnConfirm = (ImageButton) this.findViewById(R.id.btn_confirm)).setOnClickListener(this);
        (this.btnAdd = (ImageButton) this.findViewById(R.id.btn_add)).setOnClickListener(this);
        (this.btnCalculate = (ImageButton) this.findViewById(R.id.btn_calculate_)).setOnClickListener(this);
        (this.btnProjrctList = (ImageButton) this.findViewById(R.id.btn_project)).setOnClickListener(this);
        this.subNetMaskNum = (TextView) this.findViewById(R.id.subnet_mask_num);
        this.resMask.setType(IpEditText.TYPE.MASK);
        this.resMask.setDoWhitChanged(new IpEditText.DoWhitChanged() {
            @Override
            public void doSth() {
                String mask = MainActivity.this.resMask.getText().toString().trim();
                if(!TextUtils.isEmpty(mask)){
                    MainActivity.this.subNetMaskNum.setText(IPv4Util.getMask(Integer.valueOf(mask)));
                }
            }
        });
    }

    private void showGuide() {
        if (((MyApplication) this.getApplication()).isWant2Guide()) {
            ((MyApplication) this.getApplication()).isShowed = true;
            this.startActivity(new Intent(this.getApplication(), (Class) GuideActivity.class).addFlags(67108864));
        }
    }

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_confirm:
                if (!this.subNetNum.getText().toString().equals("")) {
                    this.subnetCount = Integer.parseInt(this.subNetNum.getText().toString());
                    this.subnetSetting.setVisibility(VISIBLE);
                    this.stepsDeclare.setVisibility(GONE);
                    this.params = new ArrayList<>();
                    for (int i = this.subnetCount; i > 0; --i) {
                        this.params.add(new SubNetInfomationBeanDto());
                    }
                    this.subnetSettingAdapter = new SubnetSettingAdapter(this.mContext, this.params);
                    this.subnetSetting.setAdapter(this.subnetSettingAdapter);
                    this.AdjustListView2Full(this.subnetSetting);
                    return;
                }
                SuperToast.makeText(this.mContext, this.getString(R.string.warning), this.getString(R.string.Subnet_number_cannot_be_empty), 1000).showInCenter();
            break;
            case R.id.btn_calculate_:
                if (this.subnetSetting.getAdapter() == null) {
                    SuperToast.makeText(this.mContext, this.getString(R.string.warning), this.getString(R.string.Please_finish_configuring_before_computing), 2000).showInCenter();
                    return;
                }
                this.receiveParam = ((SubnetSettingAdapter) this.subnetSetting.getAdapter()).getData();
                final ArrayList<List<SubNetInfomationBeanDto>> list = new ArrayList<>();
                final Intent intent = new Intent(this.mContext, (Class) ResultActivity.class);
                final Bundle bundle = new Bundle();
                list.add(this.receiveParam);
                bundle.putParcelableArrayList("data", (ArrayList) list);
                bundle.putString("IpSrc", this.getIPSrc());
                bundle.putString("resMask", this.resMask.getText().toString());
                intent.putExtra("bundle", bundle);
                if (this.checkInputAndSetting()) {
                    this.startActivity(intent);
                    return;
                }
            break;

            case R.id.btn_add:
                if (this.subnetSetting.isShown()) {
                    this.receiveParam = ((SubnetSettingAdapter) this.subnetSetting.getAdapter()).getData();
                    this.params.add(new SubNetInfomationBeanDto());
                    Log.i("params", new StringBuilder(String.valueOf(this.receiveParam.size())).toString());
                    MainActivity.this.subNetNum.setText(String.valueOf(Integer.valueOf(MainActivity.this.subNetNum.getText().toString().trim())+1));
                    this.subnetSettingAdapter.notifyDataSetChanged();
                    this.AdjustListView2Full(this.subnetSetting);
                    return;
                }
                SuperToast.makeText(this.mContext, this.getString(R.string.warning), this.getString(R.string.Your_input_does_not_satisfy_the_current_demand_please_check_and_reenter), 1000).showInCenter();
            break;

            case R.id.btn_project:
                this.startActivity(new Intent(this.mContext, (Class) LocalProjectActivity.class));
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.activity_main);
        ((MainActivity) (this.mContext = this)).initView();
        this.showGuide();
    }
}
