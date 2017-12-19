package com.jasperhale.myprivacy.Activity.item;

import android.databinding.Bindable;
import android.widget.Toast;

import com.jasperhale.myprivacy.Activity.Base.MyApplicantion;
import com.jasperhale.myprivacy.BR;
import com.jasperhale.myprivacy.R;

/**
 * Created by ZHANG on 2017/11/29.
 */

public class AppSetting_cell extends BaseItem {
    @Override
    public int getViewType() {
        return R.layout.item_cell;
    }

    private boolean CellInfo = false;
    private int mcc = 460;
    private int mnc = 1;
    private int lac = 41070;
    private int cid = 8309067;
    private String smcc = "460";
    private String smnc = "1";
    private String slac = "41070";
    private String scid = "8309067";

    @Bindable
    public boolean getCellInfo() {
        return CellInfo;
    }

    public void setCellInfo(boolean bool) {
        this.CellInfo = bool;
        notifyPropertyChanged(BR.cellInfo);
    }

    @Bindable
    public int getMcc() {
        return mcc;
    }

    public void setMcc(int mcc) {
        this.mcc = mcc;
        notifyPropertyChanged(BR.mcc);
    }

    @Bindable
    public int getMnc() {
        return mnc;
    }

    public void setMnc(int mnc) {
        this.mnc = mnc;
        notifyPropertyChanged(BR.mnc);
    }

    @Bindable
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
        notifyPropertyChanged(BR.cid);
    }

    @Bindable
    public int getLac() {
        return lac;
    }

    public void setLac(int lac) {
        this.lac = lac;
        notifyPropertyChanged(BR.lac);
    }

    @Bindable
    public String getSmcc() {
        return String.valueOf(mcc);
    }

    public void setSmcc(String smcc) {
        try {
            this.mcc = Integer.valueOf(smcc);
        } catch (NumberFormatException er) {
            Toast.makeText(MyApplicantion.getContext(), "请输入整数类型", Toast.LENGTH_SHORT).show();
        } finally {
            notifyPropertyChanged(BR.smcc);
        }
    }

    @Bindable
    public String getSmnc() {
        return String.valueOf(mnc);
    }

    public void setSmnc(String smnc) {
        try {
            this.mnc = Integer.valueOf(smnc);
        } catch (NumberFormatException er) {
            Toast.makeText(MyApplicantion.getContext(), "请输入整数类型", Toast.LENGTH_SHORT).show();
        } finally {
            notifyPropertyChanged(BR.smnc);
        }
    }

    @Bindable
    public String getScid() {
        return String.valueOf(cid);
    }

    public void setScid(String scid) {
        try {
            this.cid = Integer.valueOf(scid);
        } catch (NumberFormatException er) {
            Toast.makeText(MyApplicantion.getContext(), "请输入整数类型", Toast.LENGTH_SHORT).show();
        } finally {
            notifyPropertyChanged(BR.scid);
        }
    }

    @Bindable
    public String getSlac() {
        return String.valueOf(lac);
    }

    public void setSlac(String slac) {
        try {
            this.lac = Integer.valueOf(slac);
        } catch (NumberFormatException er) {
            Toast.makeText(MyApplicantion.getContext(), "请输入整数类型", Toast.LENGTH_SHORT).show();
        } finally {
            notifyPropertyChanged(BR.slac);
        }
    }
}
