package com.cp.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * cp 2019-09-13  19:02
 */
public class OrderList {
    private String id;
    private Date time;
    private String name;
    private String phone;
    private String detail;
    private BigDecimal money;
    private String status;

    public OrderList(String id, Date time, String name, String phone, String detail, BigDecimal money,String status) {
        this.id = id;
        this.time = time;
        this.name = name;
        this.phone = phone;
        this.detail = detail;
        this.money = money;
        this.status=status;
    }

    public OrderList() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
}
