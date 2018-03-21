package com.jd.cardapp.model;

import java.util.Date;

public class User {
    private Integer id;

    private String nickname;

    private String realname;

    private String password;

    private String tel;

    private Integer gender;

    private String selfemail;

    private String position;

    private String coemail;

    private String coname;

    private Integer coteam;

    private String coaddress;

    private String cowww;

    private Double balance;

    private String detail;

    private Integer ulevel;

    private Integer state;

    private Date updatetime;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSelfemail() {
        return selfemail;
    }

    public void setSelfemail(String selfemail) {
        this.selfemail = selfemail == null ? null : selfemail.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getCoemail() {
        return coemail;
    }

    public void setCoemail(String coemail) {
        this.coemail = coemail == null ? null : coemail.trim();
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname == null ? null : coname.trim();
    }

    public Integer getCoteam() {
        return coteam;
    }

    public void setCoteam(Integer coteam) {
        this.coteam = coteam;
    }

    public String getCoaddress() {
        return coaddress;
    }

    public void setCoaddress(String coaddress) {
        this.coaddress = coaddress == null ? null : coaddress.trim();
    }

    public String getCowww() {
        return cowww;
    }

    public void setCowww(String cowww) {
        this.cowww = cowww == null ? null : cowww.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getUlevel() {
        return ulevel;
    }

    public void setUlevel(Integer ulevel) {
        this.ulevel = ulevel;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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