package com.gdut.ipdivider.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.gdut.ipdivider.R;

/**
 * <p>Title: 9game</p>
 * <p>
 * <p>Description: </p>
 * //TODO Description
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>
 * <p>Company: 9game.cn</p>
 *
 * @author xinxiao.cxx@alibaba-inc.com
 * @version 1.0
 * @Date: 2017/3/15 11:29
 */
public class BaseActivity extends Activity implements View.OnClickListener {
    private Context mContext;

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.btn_back:
                this.onBackPressed();
                break;
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        this.mContext = this;
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        return true;
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }
}

