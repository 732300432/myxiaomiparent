package com.cp.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * cp 2019-09-10  14:30
 */
public class Order {
    private String id;
    private Integer uid;
    private BigDecimal money;
    private String status;
    private Date time;
    private Integer aid;

    public Order(String id, Integer uid, BigDecimal money, String status, Date time, Integer aid) {
        this.id = id;
        this.uid = uid;
        this.money = money;
        this.status = status;
        this.time = time;
        this.aid = aid;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }
}
