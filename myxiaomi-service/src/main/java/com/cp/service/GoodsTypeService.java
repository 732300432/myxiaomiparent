package com.cp.service;

import com.cp.pojo.GoodsType;

import java.util.List;

/**
 * cp 2019-09-11  12:54
 */
public interface GoodsTypeService {
    List<GoodsType> getGoodsType(int level);

    List<GoodsType> findAllGoodsType();
}
