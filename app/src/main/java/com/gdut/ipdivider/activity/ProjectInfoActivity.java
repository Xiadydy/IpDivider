package com.gdut.ipdivider.activity;

import android.content.*;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.adapter.*;
import android.widget.*;
import android.view.*;
import com.gdut.ipdivider.serialization.*;
import com.gdut.ipdivider.constants.*;
import java.io.*;
import com.gdut.ipdivider.view.*;
import android.os.*;

public class ProjectInfoActivity extends BaseActivity implements View.OnClickListener
{
    private ImageButton btnBcak;
    private ImageButton btnExport;
    private Context mContext;
    private Project project;
    private ProjectInfoAdapter projectInfoAdapter;
    private ListView subnetListView;
    private TextView titleText;

    private void getIntentData() {
        this.project = (Project) this.getIntent().getParcelableArrayListExtra("project").get(0);
    }

    private void initView() {
        this.subnetListView = (ListView)this.findViewById(R.id.subnet_listview);
        this.titleText = (TextView)this.findViewById(R.id.project_name);
        this.btnBcak = (ImageButton)this.findViewById(R.id.btn_back);
        this.btnExport = (ImageButton)this.findViewById(R.id.btn_export);
        this.btnBcak.setOnClickListener(this);
        this.btnExport.setOnClickListener(this);
    }

    private void setData() {
        System.out.println(this.project.getProjectName());
        System.out.println("titleText = " + (this.titleText == null));
        this.titleText.setText(this.project.getProjectName());
        this.projectInfoAdapter = new ProjectInfoAdapter(this.mContext, this.project.getResult());
        this.subnetListView.setAdapter(this.projectInfoAdapter);
    }

    @Override
    public void onClick(final View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btn_export:
                final Dialog dialog = new Dialog(this.mContext, this.getString(R.string.export_solution_name));
                dialog.setOnSaveListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        final TextProjectBuilder textProjectBuilder = new TextProjectBuilder();
                        final String text = dialog.getText();
                        if (!text.trim().equals("")) {
                            textProjectBuilder.exportProject(ProjectInfoActivity.this.project, text);
                            dialog.dismiss();
                            SuperToast.makeText(ProjectInfoActivity.this.mContext, ProjectInfoActivity.this.getString(R.string.export_success), String.valueOf(ProjectInfoActivity.this.getString(R.string.export_path)) + ":\n" + Constant.Storage.PROJECT_TEXT_PATH_ABSOLUTE + File.separator + text + ".xls", 2500).showInCenter();
                            return;
                        }
                        SuperToast.makeText(ProjectInfoActivity.this.mContext, ProjectInfoActivity.this.getString(R.string.warning), ProjectInfoActivity.this.getString(R.string.Name_cannot_be_empty), 1000).showInCenter();
                    }
                });
                dialog.setOnCancelListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            break;
        }
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().addFlags(1024);
        this.setContentView(R.layout.activity_project_info);
        ((ProjectInfoActivity)(this.mContext = this)).getIntentData();
        this.initView();
        this.setData();
    }

}
