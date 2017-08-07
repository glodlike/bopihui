package com.wzsykj.wuyaojiu.entity;

/**
 * Created by chen on 16/8/18.
 */
public class UserMenuEntity extends BaseEntity {
    private String tiitle;
    private int icon;
    private String info;

    public String getTiitle() {
        return tiitle;
    }

    public void setTiitle(String tiitle) {
        this.tiitle = tiitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
