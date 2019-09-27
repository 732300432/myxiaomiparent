package com.cp.mapper;

import com.cp.pojo.Cart;

import java.util.List;

/**
 * cp 2019-09-11  20:43
 */
public interface CartMapper {
    Cart findCartByIdAndPid(int id, int pid);

    void addCart(Cart cart);

    void updateCart(Cart cart);

    List<Cart> findCartById(Integer id);

    void deleteCartByIdAndPid(Integer id, int pid);

    void deleteCartById(Integer id);
}
