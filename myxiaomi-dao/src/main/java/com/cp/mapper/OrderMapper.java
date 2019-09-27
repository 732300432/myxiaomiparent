package com.cp.mapper;

import com.cp.pojo.Order;
import com.cp.pojo.OrderList;

import java.util.List;

/**
 * cp 2019-09-12  17:31
 */
public interface OrderMapper {
    void addOrder(Order order) throws Exception;

    void updateStatus(String oid, String s);

    List<OrderList> findOrderById(Integer id);
}
