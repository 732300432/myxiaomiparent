package com.cp.service.impl;

import com.cp.mapper.CartMapper;
import com.cp.mapper.OrderDetailMapper;
import com.cp.mapper.OrderMapper;
import com.cp.pojo.Order;
import com.cp.pojo.OrderDetail;
import com.cp.pojo.OrderList;
import com.cp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * cp 2019-09-12  16:57
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public void commitOrder(ArrayList<OrderDetail> orderDetails, Order order) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
//        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
//        OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
        //开启事务
        try {
//            DataSourceUtils.startTransaction();

            //1.添加订单
            orderMapper.addOrder(order);
//            int i =1/0;
            //2.添加订单详情
            for (OrderDetail orderDetail : orderDetails) {
                orderDetailMapper.addOrderDetail(orderDetail);
            }

            //3清空购物车
            cartMapper.deleteCartById(order.getUid());
//            sqlSession.commit();
//            System.out.println(Thread.currentThread().getName());
//            sqlSession.commit();

//            DataSourceUtils.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
//                sqlSession.rollback();
//                DataSourceUtils.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            try {
//                sqlSession.close();
//                DataSourceUtils.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateStatus(String oid, String s) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        orderMapper.updateStatus(oid,s);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public List<OrderList> findOrderById(Integer id) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        List<OrderList> lists = orderMapper.findOrderById(id);
//        sqlSession.close();
        return lists;
    }
}
