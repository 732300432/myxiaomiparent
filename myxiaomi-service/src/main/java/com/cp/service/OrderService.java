package com.cp.service;

import com.cp.pojo.Order;
import com.cp.pojo.OrderDetail;
import com.cp.pojo.OrderList;

import java.util.ArrayList;
import java.util.List;

/**
 * cp 2019-09-12  16:57
 */
public interface OrderService {
    void commitOrder(ArrayList<OrderDetail> orderDetails, Order order);

    void updateStatus(String oid, String s);

    List<OrderList> findOrderById(Integer id);
}
