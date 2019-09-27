package com.cp.service.impl;

import com.cp.mapper.CartMapper;
import com.cp.mapper.GoodsMapper;
import com.cp.pojo.Cart;
import com.cp.pojo.Goods;
import com.cp.service.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cp 2019-09-11  20:35
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class CartServiceImpl implements CartsService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public Cart getCartById(int id, int pid) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
        Cart cart = cartMapper.findCartByIdAndPid(id, pid);
//        sqlSession.close();
        return cart;
    }

    @Override
    public void addCart(Cart cart) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);

        cartMapper.addCart(cart);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public void updateCart(Cart cart) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
        cartMapper.updateCart(cart);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public List<Cart> findCartById(Integer id) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
        List<Cart> carts = cartMapper.findCartById(id);

//        GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
        //根据商品id获取商品信息
        for (Cart cart : carts) {
            Goods goods = goodsMapper.findGoodsById(cart.getPid());
            cart.setGoods(goods);
        }
//        sqlSession.close();


        return carts;
    }

    @Override
    public void deleteCartByIdAndPid(Integer id, int pid) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
        cartMapper.deleteCartByIdAndPid(id,pid);
//        sqlSession.commit();
//        sqlSession.close();
    }

    @Override
    public void deleteCartById(Integer id) {
//        SqlSession sqlSession = MyBatisUtils.openSession();
//        CartMapper cartMapper = sqlSession.getMapper(CartMapper.class);
        cartMapper.deleteCartById(id);
//        sqlSession.commit();
//        sqlSession.close();
    }

}
