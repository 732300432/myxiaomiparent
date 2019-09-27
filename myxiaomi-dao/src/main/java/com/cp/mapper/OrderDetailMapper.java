package com.cp.mapper;

import com.cp.pojo.OrderDetail;

import java.util.List;

/**
 * cp 2019-09-12  17:09
 */
public interface OrderDetailMapper {
    void addOrderDetail(OrderDetail od) throws Exception;

    List<OrderDetail> findOrderDetail(String oid);
}
