package com.cp.service.impl;

import com.cp.mapper.GoodsMapper;
import com.cp.mapper.GoodsTypeMapper;
import com.cp.pojo.Goods;
import com.cp.pojo.GoodsType;
import com.cp.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cp 2019-09-11  12:53
 */
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsTypeMapper goodsTypeMapper;
    @Override
    public List<Goods> findPageByWhere( String condition) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        List<Goods> goodsList = goodsMapper.findPageByWhere(condition);
        long totalSize = goodsMapper.findGoodsCount(condition);
//        sqlSession.close();
        return goodsList;
    }

    @Override
    public Goods findGoodsById(int id) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
//        GoodsTypeMapper goodsTypeMapper = sqlSession.getMapper(GoodsTypeMapper.class);
        //得到商品信息
        Goods goods = goodsMapper.findGoodsById(id);
        //根据商品类别id得到商品类别id
        GoodsType goodsType = goodsTypeMapper.findGoodsTypeById(goods.getTypeid());
        //为商品类别赋值
        goods.setTypeName(goodsType.getName());
//        sqlSession.close();
        return goods;
    }
}
