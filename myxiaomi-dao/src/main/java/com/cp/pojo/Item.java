package com.cp.pojo;

import java.math.BigDecimal;

/**
 * cp 2019-09-13  19:20
 */
public class Item {
    private Goods goods;
    private Integer num;
    private BigDecimal money;

    public Item(Goods goods, Integer num, BigDecimal money) {
        this.goods = goods;
        this.num = num;
        this.money = money;
    }

    public Item() {
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
