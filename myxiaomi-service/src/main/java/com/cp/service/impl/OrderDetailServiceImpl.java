package com.cp.service.impl;

import com.cp.mapper.GoodsMapper;
import com.cp.mapper.OrderDetailMapper;
import com.cp.pojo.Goods;
import com.cp.pojo.Item;
import com.cp.pojo.OrderDetail;
import com.cp.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * cp 2019-09-12  17:08
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<Item> findOrderDetail(String oid) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
//        OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
        //得到商品详情
        List<Item> items = new LinkedList<>();

        List<OrderDetail> orderDetails = orderDetailMapper.findOrderDetail(oid);
        for (OrderDetail orderDetail : orderDetails) {

            Goods goods = goodsMapper.findGoodsById(orderDetail.getPid());
            Item item = new Item(goods, orderDetail.getNum(), orderDetail.getMoney());
            items.add(item);
        }
//        sqlSession.close();
        return items;
    }
}
