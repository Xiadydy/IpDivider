package com.gdut.ipdivider.view;

import android.app.*;
import android.content.*;
import android.os.*;
import android.widget.*;
import android.view.*;

import com.gdut.ipdivider.R;

public class ConfirmDialog extends AlertDialog
{
    private Button btnCancle;
    private Button btnConfirm;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private Handler mHandler;
    private TextView message;
    private String strTitle;
    private TextView title;

    public ConfirmDialog(final Context mContext, final String strTitle) {
        super(mContext);
        this.mContext = mContext;
        this.mHandler = this.mHandler;
        this.strTitle = strTitle;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.initView();
    }

    private void initView() {
        final View inflate = this.layoutInflater.inflate(R.layout.confirm_dialog_layout, null);
        this.message = (TextView)inflate.findViewById(R.id.content);
        (this.title = (TextView)inflate.findViewById(R.id.title)).setText(this.strTitle);
        this.btnConfirm = (Button)inflate.findViewById(R.id.btnConfirm);
        this.btnCancle = (Button)inflate.findViewById(R.id.btn_cancle);
        this.setView(inflate);
    }

    public void setMessage(final CharSequence text) {
        this.message.setText(text);
    }

    public void setOnCancleListener(final View.OnClickListener onClickListener) {
        this.btnCancle.setOnClickListener(onClickListener);
    }

    public void setOnConfirmListener(final View.OnClickListener onClickListener) {
        this.btnConfirm.setOnClickListener(onClickListener);
    }
}
