package com.ant.vxserver.pojo;

import java.io.Serializable;
import java.util.Date;

public class FlUser implements Serializable {
    private Integer id;

    private Integer leader;

    private String weichatname;

    private String weichatgroup;

    private String phone;

    private String flAccount;

    private Integer role;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeader() {
        return leader;
    }

    public void setLeader(Integer leader) {
        this.leader = leader;
    }

    public String getWeichatname() {
        return weichatname;
    }

    public void setWeichatname(String weichatname) {
        this.weichatname = weichatname == null ? null : weichatname.trim();
    }

    public String getWeichatgroup() {
        return weichatgroup;
    }

    public void setWeichatgroup(String weichatgroup) {
        this.weichatgroup = weichatgroup == null ? null : weichatgroup.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFlAccount() {
        return flAccount;
    }

    public void setFlAccount(String flAccount) {
        this.flAccount = flAccount == null ? null : flAccount.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", leader=").append(leader);
        sb.append(", weichatname=").append(weichatname);
        sb.append(", weichatgroup=").append(weichatgroup);
        sb.append(", phone=").append(phone);
        sb.append(", flAccount=").append(flAccount);
        sb.append(", role=").append(role);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}