package com.gdut.ipdivider.activity;

import java.util.*;
import android.content.*;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.adapter.*;
import android.os.*;
import android.view.View.OnClickListener;
import android.widget.*;
import com.gdut.ipdivider.tool.*;
import android.view.*;
import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.constants.*;
import java.io.*;
import com.gdut.ipdivider.view.*;
import com.gdut.ipdivider.serialization.*;

public class ResultActivity extends BaseActivity implements OnClickListener
{
    private ImageButton btnBack;
    private ImageButton btnExport;
    private ImageButton btnSave;
    private Intent intent;
    private ListView listView;
    private Context mContext;
    private String projectName;
    private ResultAdapter resultAdapter;
    private Dialog toTextdialog;
    //lvdi start
    private String ip;
    private List<SubNetInfomationBean> result;
    private String resMask;
    private List<SubNetInfomationBeanDto> param;
    private SubNetInfomationVo vo;

    public ResultActivity() {
        this.resMask = "";
        this.ip = "";
    }

    private void initData() {
        this.intent = this.getIntent();
        final Bundle bundleExtra = this.intent.getBundleExtra("bundle");
        this.param = (List<SubNetInfomationBeanDto>)bundleExtra.getParcelableArrayList("data").get(0);
        this.ip = bundleExtra.getString("IpSrc");
        this.resMask = bundleExtra.getString("resMask");
        this.vo = new SubNetInfomationVo();
        vo.setIPAdress(ip);
        vo.setMask(resMask);
        vo.setSubNet(this.param);
    }

    private void initListView() {
        this.resultAdapter = new ResultAdapter(this.mContext, this.result);
        this.listView.setAdapter(this.resultAdapter);
    }

    private void initView() {
        mContext = this;
        this.btnBack = (ImageButton)this.findViewById(R.id.btn_back);
        this.btnExport = (ImageButton)this.findViewById(R.id.btn_export);
        this.btnSave = (ImageButton)this.findViewById(R.id.btn_save);
        this.listView = (ListView)this.findViewById(R.id.result_listview);
        this.btnBack.setOnClickListener(this);
        this.btnSave.setOnClickListener(this);
        this.btnExport.setOnClickListener(this);
    }

    private void setData() {
        IPService service = new IPService();
        List<SubNetInfomationBeanDto> dtos = service.getAllFiledOfBean(this.vo);
        this.result = service.getResult(dtos);
    }

    @Override
    public void onClick(final View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_export:
                (this.toTextdialog = new Dialog(this.mContext, this.getString(R.string.export_solution_name))).setOnSaveListener(new OnClickListener() {
                    public void onClick(final View view) {
                        final TextProjectBuilder textProjectBuilder = new TextProjectBuilder();
                        if (!ResultActivity.this.toTextdialog.getText().trim().equals("")) {
                            String project = toTextdialog.getText();
                            textProjectBuilder.deconstruct(new Project(project, ResultActivity.this.result));
                            ResultActivity.this.toTextdialog.dismiss();
                            SuperToast.makeText(ResultActivity.this.mContext, ResultActivity.this.getString(R.string.export_success), String.valueOf(ResultActivity.this.getString(R.string.export_path)) + ":\n" + Constant.Storage.PROJECT_TEXT_PATH_ABSOLUTE + File.separator + project + ".xls", 2500).showInCenter();
                            return;
                        }
                        System.err.println("projectName is null");
                        SuperToast.makeText(ResultActivity.this.mContext, ResultActivity.this.getString(R.string.warning), ResultActivity.this.getString(R.string.Name_cannot_be_empty), 1000).showInCenter();
                    }
                });
                this.toTextdialog.setOnCancelListener(new OnClickListener() {
                    public void onClick(final View view) {
                        ResultActivity.this.toTextdialog.dismiss();
                    }
                });
                if (!this.isFinishing()) {
                    this.toTextdialog.show();
                }
            break;

            case R.id.btn_save:
                final Dialog dialog = new Dialog(this.mContext, this.getString(R.string.solution_name));
                dialog.setOnSaveListener(new OnClickListener() {
                    public void onClick(final View view) {
                        if (!dialog.getText().trim().equals("")) {
                            new ProjectBuilder().deconstruct(new Project(dialog.getText(), ResultActivity.this.result));
                            SuperToast.makeText(ResultActivity.this.mContext, "保存成功", "已把本方案保存在本地", 1000).showInCenter();
                            dialog.dismiss();
                            return;
                        }
                        System.err.println("projectName is null");
                        SuperToast.makeText(ResultActivity.this.mContext, ResultActivity.this.getString(R.string.warning), ResultActivity.this.getString(R.string.Name_cannot_be_empty), 1000).showInCenter();
                    }
                });
                dialog.setOnCancelListener(new OnClickListener() {
                    public void onClick(final View view) {
                        dialog.dismiss();
                    }
                });
                if (!this.isFinishing()) {
                    dialog.show();
                }
            break;

            default:
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().addFlags(1024);
        this.setContentView(R.layout.activity_result);
        initView();
        this.initData();
        this.setData();
        this.initListView();
    }
}
