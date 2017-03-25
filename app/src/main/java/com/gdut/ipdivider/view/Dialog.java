package com.gdut.ipdivider.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gdut.ipdivider.R;

public class Dialog
        extends AlertDialog
{
    private Button btnCancle;
    private Button btnSave;
    private EditText editText;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private Handler mHandler;
    private View mView;
    private String strTitle;
    private TextView title;

    public Dialog(Context paramContext, Handler paramHandler, String paramString)
    {
        super(paramContext);
        this.mContext = paramContext;
        this.mHandler = paramHandler;
        this.strTitle = paramString;
        this.layoutInflater = LayoutInflater.from(paramContext);
        initView();
    }

    public Dialog(Context paramContext, String paramString)
    {
        super(paramContext);
        this.mContext = paramContext;
        this.mHandler = this.mHandler;
        this.strTitle = paramString;
        this.layoutInflater = LayoutInflater.from(paramContext);
        initView();
    }

    private void initView()
    {
        this.mView = this.layoutInflater.inflate(R.layout.dialog_layout, null);
        this.title = ((TextView)this.mView.findViewById(R.id.title));
        this.title.setText(this.strTitle);
        this.editText = ((EditText)this.mView.findViewById(R.id.edite_name));
        this.editText.setFocusable(true);
        this.btnSave = ((Button)this.mView.findViewById(R.id.btn_save));
        this.btnCancle = ((Button)this.mView.findViewById(R.id.btn_cancle));
        setView(this.mView, 0, 0, 0, 0);
    }

    public String getText()
    {
        return this.editText.getText().toString();
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
    }

    public void setOnCancelListener(View.OnClickListener paramOnClickListener)
    {
        this.btnCancle.setOnClickListener(paramOnClickListener);
    }

    public void setOnSaveListener(View.OnClickListener paramOnClickListener)
    {
        this.btnSave.setOnClickListener(paramOnClickListener);
    }
}
