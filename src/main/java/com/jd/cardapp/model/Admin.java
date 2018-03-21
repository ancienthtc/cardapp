package com.jd.cardapp.model;

import java.util.Date;

public class Admin {
    private Integer id;

    private String admin;

    private String apass;

    private String name;

    private Integer alevel;

    private Date alogin;

    private Date updatetime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin == null ? null : admin.trim();
    }

    public String getApass() {
        return apass;
    }

    public void setApass(String apass) {
        this.apass = apass == null ? null : apass.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAlevel() {
        return alevel;
    }

    public void setAlevel(Integer alevel) {
        this.alevel = alevel;
    }

    public Date getAlogin() {
        return alogin;
    }

    public void setAlogin(Date alogin) {
        this.alogin = alogin;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}