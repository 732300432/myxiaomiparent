package com.cp.service;

import com.cp.pojo.Item;

import java.util.List;

/**
 * cp 2019-09-12  17:08
 */
public interface OrderDetailService {
    List<Item> findOrderDetail(String oid);
}
