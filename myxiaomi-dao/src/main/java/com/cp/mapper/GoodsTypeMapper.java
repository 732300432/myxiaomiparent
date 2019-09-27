package com.cp.mapper;

import com.cp.pojo.GoodsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * cp 2019-09-11  13:06
 */
public interface GoodsTypeMapper {
    List<GoodsType> findAllGoodsType(@Param("level") int level);
    List<GoodsType> findAllGoodsType();

    GoodsType findGoodsTypeById(int id);

    String findGoodTypeParentName(Integer parent);
}
