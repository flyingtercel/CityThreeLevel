package com.mifeng.us.bean;

/**
 * Created by night_slight on 2019/4/15.
 */

public class AreaBean {

    /**
     * label : 110101
     * value : 东城区
     */

    private String label;
    private String value;

    private boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
