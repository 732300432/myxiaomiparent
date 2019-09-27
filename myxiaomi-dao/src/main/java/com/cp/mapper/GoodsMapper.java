package com.cp.mapper;

import com.cp.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * cp 2019-09-11  17:11
 */
public interface GoodsMapper {
    List<Goods> findPageByWhere( @Param("condition") String condition);

    long findGoodsCount(@Param("condition")String condition);

    Goods findGoodsById(int id);
}
