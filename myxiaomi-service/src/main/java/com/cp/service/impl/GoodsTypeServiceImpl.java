package com.cp.service.impl;

import com.cp.mapper.GoodsTypeMapper;
import com.cp.pojo.GoodsType;
import com.cp.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cp 2019-09-11  12:55
 */
@Transactional(rollbackFor =Exception.class)
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<GoodsType> getGoodsType(int level) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        GoodsTypeMapper goodsTypeMapper = sqlSession.getMapper(GoodsTypeMapper.class);
        List<GoodsType> goodsType = goodsTypeMapper.findAllGoodsType(level);
//        sqlSession.close();
        return goodsType;
    }

    @Override
    public List<GoodsType> findAllGoodsType() {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        GoodsTypeMapper goodsTypeMapper = sqlSession.getMapper(GoodsTypeMapper.class);
        List<GoodsType> goodsTypes = goodsTypeMapper.findAllGoodsType();
        for (GoodsType goodsType : goodsTypes) {
            GoodsType gt = goodsTypeMapper.findGoodsTypeById(goodsType.getParent());
            if(gt!=null){
                goodsType.setParentName(gt.getName());
            }else {
                goodsType.setParentName("无");
            }
        }
//        sqlSession.close();
        return goodsTypes;
    }
}
