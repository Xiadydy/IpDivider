package com.gdut.ipdivider.view;

import android.widget.*;
import android.content.*;
import android.util.*;
import android.text.*;

import com.gdut.ipdivider.R;

public class IpEditText extends EditText implements TextWatcher
{
    public static final int DEFAULT = 0;
    private TYPE Type;
    private DoWhitChanged doWhitChanged;
    private Context mContext;

//    static /* synthetic */ int[] $SWITCH_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE() {
//        final int[] $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE = IpEditText.$SWITCH_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE;
//        if ($switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE != null) {
//            return $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE;
//        }
//        final int[] $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE2 = new int[TYPE.values().length];
//        while (true) {
//            try {
//                $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE2[TYPE.DEFAULT.ordinal()] = 3;
//                try {
//                    $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE2[TYPE.IP.ordinal()] = 2;
//                    try {
//                        $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE2[TYPE.MASK.ordinal()] = 1;
//                        try {
//                            $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE2[TYPE.NETSUBNUM.ordinal()] = 4;
//                            return IpEditText.$SWITCH_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE = $switch_TABLE$com$gdut$ipdivider$view$IpEditText$TYPE2;
//                        }
//                        catch (NoSuchFieldError noSuchFieldError) {}
//                    }
//                    catch (NoSuchFieldError noSuchFieldError2) {}
//                }
//                catch (NoSuchFieldError noSuchFieldError3) {}
//            }
//            catch (NoSuchFieldError noSuchFieldError4) {
//                continue;
//            }
//            break;
//        }
//    }

    public IpEditText(final Context mContext) {
        super(mContext);
        this.Type = TYPE.DEFAULT;
        this.mContext = mContext;
        this.Type = TYPE.DEFAULT;
    }

    public IpEditText(final Context mContext, final AttributeSet set) {
        super(mContext, set);
        this.Type = TYPE.DEFAULT;
        this.mContext = mContext;
        this.Type = TYPE.DEFAULT;
    }

    public void afterTextChanged(final Editable editable) {
    }

    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }

    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        super.onTextChanged(charSequence, n, n2, n3);
        if (this.Type != null) {
            switch (this.Type.num) {
                case 3: {
                    if (!charSequence.toString().equals("") && Integer.valueOf(charSequence.toString()) > 50) {
                        SuperToast.makeText(this.mContext, this.mContext.getString(R.string.warning), this.mContext.getString(R.string.Subnet_number_suggested_not_greater_than_50), 1000).showInCenter();
                        this.setText("50");
                        this.setCursorIndex(this.getText().toString().length());
                    }
                    if (this.doWhitChanged != null) {
                        this.doWhitChanged.doSth();
                        return;
                    }
                    break;
                }
                case 1: {
                    if (!charSequence.toString().equals("") && Integer.valueOf(charSequence.toString()) > 255) {
                        SuperToast.makeText(this.mContext, this.mContext.getString(R.string.warning), this.mContext.getString(R.string.Cannot_be_greater_than_255), 1000).showInCenter();
                        this.setText("255");
                        this.setCursorIndex(this.getText().toString().length());
                    }
                    if (this.doWhitChanged != null) {
                        this.doWhitChanged.doSth();
                        return;
                    }
                    break;
                }
                case 0: {
                    if (!charSequence.toString().equals("") && Integer.valueOf(charSequence.toString()) > 32) {
                        SuperToast.makeText(this.mContext, this.mContext.getString(R.string.warning), this.mContext.getString(R.string.Cannot_be_greater_than_32), 1000).showInCenter();
                        this.setText("32");
                        this.setCursorIndex(this.getText().toString().length());
                    }
                    if (this.doWhitChanged != null) {
                        this.doWhitChanged.doSth();
                        return;
                    }
                    break;
                }
            }
        }
    }

    protected void setCursorIndex(final int selection) {
        this.setSelection(selection);
    }

    public void setDoWhitChanged(final DoWhitChanged doWhitChanged) {
        this.doWhitChanged = doWhitChanged;
    }

    public void setType(final TYPE type) {
        this.Type = type;
    }

    public interface DoWhitChanged
    {
        void doSth();
    }

    public enum TYPE
    {
        DEFAULT("DEFAULT", 2),
        IP("IP", 1),
        MASK("MASK", 0),
        NETSUBNUM("NETSUBNUM", 3);

        TYPE(final String s, final int n) {
            this.s = s;
            this.num = n;

        }
        public String s;
        public int num;
    }
}
