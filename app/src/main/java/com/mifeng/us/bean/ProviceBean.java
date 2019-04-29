package com.mifeng.us.bean;

import java.util.List;

/**
 * Created by night_slight on 2019/4/15.
 */

public class ProviceBean {

    /**
     * label : 11
     * value : 北京市
     * children : [{"label":"1101","value":"市辖区","children":[{"label":"110101","value":"东城区"},{"label":"110102","value":"西城区"},{"label":"110105","value":"朝阳区"},{"label":"110106","value":"丰台区"},{"label":"110107","value":"石景山区"},{"label":"110108","value":"海淀区"},{"label":"110109","value":"门头沟区"},{"label":"110111","value":"房山区"},{"label":"110112","value":"通州区"},{"label":"110113","value":"顺义区"},{"label":"110114","value":"昌平区"},{"label":"110115","value":"大兴区"},{"label":"110116","value":"怀柔区"},{"label":"110117","value":"平谷区"},{"label":"110118","value":"密云区"},{"label":"110119","value":"延庆区"}]}]
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

    private List<CityBean> children;

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


    public List<CityBean> getChildren() {
        return children;
    }

    public void setChildren(List<CityBean> children) {
        this.children = children;
    }

}

