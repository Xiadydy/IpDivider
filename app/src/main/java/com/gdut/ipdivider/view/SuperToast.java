package com.gdut.ipdivider.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gdut.ipdivider.R;

public class SuperToast extends Toast {
    private static SuperToast instance = null;
    private static TextView text;
    private static TextView title;
    private static LinearLayout titleLinearLayout;
    private LayoutInflater mLayoutInflater;
    private View mView;

    public SuperToast(Context paramContext) {
        super(paramContext);
        this.mLayoutInflater = LayoutInflater.from(paramContext);
        this.mView = this.mLayoutInflater.inflate(R.layout.toast_view, null);
        title = (TextView) mView.findViewById(R.id.title);
        text = (TextView) mView.findViewById(R.id.text);
        titleLinearLayout = (LinearLayout) mView.findViewById(R.id.linearlayout_title);
        setView(this.mView);
        instance = this;
    }

    public static SuperToast makeText(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, int paramInt) {
        instance = new SuperToast(paramContext);
        if (paramCharSequence1 != null) {
            title.setText(paramCharSequence1);
            titleLinearLayout.setVisibility(View.VISIBLE);
        }
        text.setText(paramCharSequence2);
        return instance;
    }

    public void setText(CharSequence paramCharSequence) {
        super.setText(paramCharSequence);
        text.setText(paramCharSequence);
    }

    public void setTitle(CharSequence paramCharSequence) {
        title.setText(paramCharSequence);
    }

    public void showInCenter() {
        setGravity(17, 0, 0);
        show();
    }
}
