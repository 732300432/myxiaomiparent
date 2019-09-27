package com.cp.web.servlet;

import com.cp.pojo.*;
import com.cp.service.AddressService;
import com.cp.service.CartsService;
import com.cp.service.OrderDetailService;
import com.cp.service.OrderService;
import com.cp.utils.RandomUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * cp 2019-09-12  9:34
 */
@WebServlet(value = "/orderservlet")
public class OrderServlet extends BaseServlet {
    CartsService cartsService = (CartsService) ContextLoader.getCurrentWebApplicationContext().getBean("cartServiceImpl");
    AddressService addressService = (AddressService) ContextLoader.getCurrentWebApplicationContext().getBean("addressServiceImpl");
    OrderService orderService = (OrderService) ContextLoader.getCurrentWebApplicationContext().getBean("orderServiceImpl");
    OrderDetailService orderDetailService = (OrderDetailService) ContextLoader.getCurrentWebApplicationContext().getBean("orderDetailServiceImpl");
    public String getOrderView(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }

        List<Cart> cartList = cartsService.findCartById(user.getId());
        request.setAttribute("cartList", cartList);

        List<Address> addList = addressService.findAddressByUid(user.getId());
        request.setAttribute("addList", addList);
        return "order.jsp";

    }

    public String addOrder(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }

        String aid = request.getParameter("aid");

        //查询客户购物车的商品

        List<Cart> cartList = cartsService.findCartById(user.getId());

        if (cartList == null || cartList.size() == 0) {
            request.setAttribute("msg", "购物车空空如也，再去逛逛吧");
            return "message.jsp";
        }
        //1.添加到订单详情
        BigDecimal money = new BigDecimal(0);
        //生成订单id
        String oid = RandomUtils.createOrderId();
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();
        for (Cart cart : cartList) {
            OrderDetail od = new OrderDetail(null, oid, cart.getPid(), cart.getNum(), cart.getMoney());
            orderDetails.add(od);
            money = cart.getMoney().add(money);
        }


        //2.添加到订单
        Order order = new Order(oid, user.getId(), money, "1", new Date(), Integer.parseInt(aid));


        try {
            request.setAttribute("order", order);
            orderService.commitOrder(orderDetails, order);
            return "orderSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/message.jsp";


    }

    public String getOrderList(HttpServletRequest request,HttpServletResponse response){
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:login.jsp";
        }

        //查询订单

        List<OrderList> orders = orderService.findOrderById(user.getId());
        request.setAttribute("orders",orders );


        //根据订单号查询订单详情

        List items=new ArrayList();
        for(OrderList od :orders){
            List<Item> item = orderDetailService.findOrderDetail(od.getId());//oid
            items.addAll(item);
        }
        request.setAttribute("items",items );

        return "orderDetail.jsp";


    }
}
