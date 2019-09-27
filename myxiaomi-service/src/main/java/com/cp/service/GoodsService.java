package com.cp.service;

import com.cp.pojo.Goods;

import java.util.List;

/**
 * cp 2019-09-11  12:52
 */
public interface GoodsService {
    List<Goods> findPageByWhere(String condition);

    Goods findGoodsById(int id);
}
