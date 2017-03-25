package com.gdut.ipdivider.activity;

import com.gdut.ipdivider.R;
import com.gdut.ipdivider.adapter.*;
import com.gdut.ipdivider.entity.*;
import com.gdut.ipdivider.tool.*;
import android.view.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import java.util.*;
import com.gdut.ipdivider.view.*;

public class LocalProjectActivity extends BaseActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener
{
    private ProjectListAdapter adapter;
    private ImageButton btn;
    private ImageButton btnBack;
    private List<Project> list;
    private ListView listView;
    private Context mContext;
    private TextView noResultWarnning;

    private void dimissNoResultWarnning() {
        this.noResultWarnning.setVisibility(View.GONE);
        this.listView.setVisibility(View.VISIBLE);
    }

    private void initView() {
        this.listView = (ListView)this.findViewById(R.id.project_listview);
        this.noResultWarnning = (TextView)this.findViewById(R.id.noResultWarnning);
        (this.btnBack = (ImageButton)this.findViewById(R.id.btn_back)).setOnClickListener(this);
    }

    private void initdata() {
        this.list = ProjectManager.getAllProjectFromDisk();
        if (this.list.size() == 0) {
            this.showNoResultWarnning();
            return;
        }
        if (this.noResultWarnning.isShown()) {
            this.dimissNoResultWarnning();
        }
        this.adapter = new ProjectListAdapter(this.mContext, this.list);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(this);
        this.listView.setOnItemLongClickListener(this);
    }

    private void refreshlistView() {
        this.list = ProjectManager.getAllProjectFromDisk();
        if (this.list.size() == 0) {
            this.showNoResultWarnning();
        }
        this.adapter.notifyDataSetChanged();
    }

    private void showNoResultWarnning() {
        this.noResultWarnning.setVisibility(View.VISIBLE);
        this.listView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(final View view) {
        super.onClick(view);
    }

    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().addFlags(1024);
        this.setContentView(R.layout.activity_local_project);
        ((LocalProjectActivity)(this.mContext = this)).initView();
        this.initdata();
    }

    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        switch (adapterView.getId()) {
            case R.id.project_listview: {
                System.out.println("onItemLongClick  project_listview");
                final Project item = ((ProjectListAdapter)adapterView.getAdapter()).getItem(n);
                final Intent intent = new Intent(this.mContext, (Class)ProjectInfoActivity.class);
                final ArrayList<Project> list = new ArrayList<Project>();
                list.add(item);
                intent.putParcelableArrayListExtra("project", (ArrayList)list);
                this.startActivity(intent);
            }
            break;
        }
    }

    public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        switch (adapterView.getId()) {
            case R.id.project_listview: {
                final ConfirmDialog confirmDialog = new ConfirmDialog(this.mContext, this.getResources().getString(R.string.delete));
                confirmDialog.setMessage(this.getResources().getString(R.string.warming_deletion));
                confirmDialog.setOnConfirmListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        ProjectManager.deleteProject(((ProjectListAdapter)adapterView.getAdapter()).getItem(n).getProjectName());
                        LocalProjectActivity.this.refreshlistView();
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.setOnCancleListener(new View.OnClickListener() {
                    public void onClick(final View view) {
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.show();
                break;
            }
        }
        return true;
    }
}
