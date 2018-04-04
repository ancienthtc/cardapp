package com.jd.cardapp.model;

import java.util.Date;

public class Buy {
    private Integer id;

    private Integer state;

    private Integer type;

    private Double pay;

    private String detail;

    private String pic1;

    private String pic2;

    private String folder1;

    private String folder2;

    private Integer card;

    private Integer user;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1 == null ? null : pic1.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
    }

    public String getFolder1() {
        return folder1;
    }

    public void setFolder1(String folder1) {
        this.folder1 = folder1 == null ? null : folder1.trim();
    }

    public String getFolder2() {
        return folder2;
    }

    public void setFolder2(String folder2) {
        this.folder2 = folder2 == null ? null : folder2.trim();
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}