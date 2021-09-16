package com.du.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdminInfo {
    private String adminName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date adminTime;
    private String adminPwd;
    private List<Lover> loverList;
    private Integer[] aihao; // 1.吃 2.喝 3.玩

    @Override
    public String toString() {
        return "AdminInfo{" +
                "adminName='" + adminName + '\'' +
                ", adminTime=" + adminTime +
                ", adminPwd='" + adminPwd + '\'' +
                ", loverList=" + loverList +
                ", aihao=" + Arrays.toString(aihao) +
                '}';
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Date getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Date adminTime) {
        this.adminTime = adminTime;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public List<Lover> getLoverList() {
        return loverList;
    }

    public void setLoverList(List<Lover> loverList) {
        this.loverList = loverList;
    }

    public Integer[] getAihao() {
        return aihao;
    }

    public void setAihao(Integer[] aihao) {
        this.aihao = aihao;
    }
}
