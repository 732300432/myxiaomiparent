package com.cp.web.servlet;

import com.cp.pojo.Cart;
import com.cp.pojo.Goods;
import com.cp.pojo.User;
import com.cp.service.CartsService;
import com.cp.service.GoodsService;
import com.cp.utils.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * cp 2019-09-11  20:19
 */
@WebServlet(value = "/cartservlet")
public class CartsServlet extends BaseServlet {
    CartsService cartsService = (CartsService) ContextLoader.getCurrentWebApplicationContext().getBean("cartServiceImpl");
    GoodsService goodsService = (GoodsService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");
    public String addCart(HttpServletRequest request, HttpServletResponse response) {
        //1判断用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            //没有登录重定向到主页
            return "redirect:index.jsp";
        }

        //登录了 从session中取出用户id
        Integer id = user.getId();
        String goodsId = request.getParameter("goodsId");
        String number = request.getParameter("num");

        int num=Integer.parseInt(number);
        if (!StringUtils.isEmpty(goodsId)) {
            int pid = Integer.parseInt(goodsId);
            //2.判断购物车是否有该商品 查询数据库
            Cart cart = cartsService.getCartById(id,pid);
            //查询商品信息

            Goods goods = goodsService.findGoodsById(pid);
            if (cart == null) {
                cart=new Cart(id,pid , num,new BigDecimal(num).multiply(goods.getPrice()));
                cartsService.addCart(cart);
                //2.1如果没有，就直接添加
            } else {
                //2.2如果有，就修改
                cart.setNum(cart.getNum()+num);
                cart.setMoney(cart.getMoney().add(new BigDecimal(num).multiply(goods.getPrice())));
                cartsService.updateCart(cart);
            }
            return "redirect:cartSuccess.jsp";
        }
        return "redirect:index.jsp";

    }

    public String getCart(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:login.jsp";
        }

        List<Cart> carts = cartsService.findCartById(user.getId());
        request.setAttribute("carts",carts );
        return "cart.jsp";

    }

    public String updateCartNum(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:login.jsp";
        }

        String _pid = request.getParameter("pid");
        String _num = request.getParameter("num");
        String _price = request.getParameter("price");
        int pid=0;
        int num=0;
        BigDecimal price=new BigDecimal(0);
        try {
            if(!StringUtils.isEmpty(_pid)){
                pid=Integer.parseInt(_pid);
            }
            if(!StringUtils.isEmpty(_num)){
                num=Integer.parseInt(_num);
            }
            if(!StringUtils.isEmpty(_price)){
                price=new BigDecimal(_price);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:login.jsp";
        }
        if(num!=0) {
            Cart cart1 = cartsService.getCartById(user.getId(), pid);
            cart1.setNum(num + cart1.getNum());
            cart1.setMoney(price.multiply(new BigDecimal(cart1.getNum())));
//        goodsService.findGoodsById(pid)
//            Cart cart = new Cart(user.getId(), pid, num, price);
            cartsService.updateCart(cart1);
        }else {
            cartsService.deleteCartByIdAndPid(user.getId(), pid);
        }
//        request.setAttribute("cart",cart );

        return null;
    }

    public String clearCart(HttpServletRequest request, HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:login.jsp";
        }
        //根据用户id清空购物车

        cartsService.deleteCartById(user.getId());
        return null;

    }
}
