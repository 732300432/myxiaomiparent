package com.cp.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cp.pojo.GoodsType;
import com.cp.pojo.User;
import com.cp.service.GoodsTypeService;
import com.cp.service.UserService;
import com.cp.service.impl.GoodsTypeServiceImpl;
import com.cp.utils.StringUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * cp 2019-09-14  10:50
 */
@WebServlet(value = "/adminservlet")
public class AdminServlet extends BaseServlet {
    UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");
    public String adminLogin(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User admin = userService.login(username, password);

        if (StringUtils.isEmpty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "admin/login.jsp";
        }
        if (StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "admin/login.jsp";
        }

        //验证是否激活
        if (admin.getFlag() != 1) {
            request.setAttribute("msg", "您还没有激活，请激活后再登录");
            return "admin/login.jsp";
        }
        //验证是否是管理员
        if (admin.getRole() != 0) {
            request.setAttribute("msg", "您没有权限，无法登录");
            return "admin/login.jsp";
        }

        if (admin!=null) {
            request.setAttribute("admin",admin );
            request.getSession().setAttribute("admin",admin );
            return "admin/admin.jsp";
        }else {
            request.setAttribute("msg","用户名或者密码不正确" );
            return "admin/login.jsp";
        }
    }

    public String getUserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");


        List<User> listUser = userService.findAllUser();

        //转换成json字符串
        String list = JSON.toJSONString(listUser);
//        System.out.println(list);
        response.getWriter().write(list);

        return null;
//        return "admin:userList.jsp";

    }
    public String deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String id = request.getParameter("id");
            userService.deleteUserById(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("删除失败",e );
        }

        return null;
    }

    public String searchUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        response.setContentType("text/html;charset=utf-8");

        List<User> userList = userService.findUserByUsernameAndGender(username,gender);
        if(userList==null||userList.size()==0){
            return null;
        }
        String json = JSON.toJSONString(userList);
        response.getWriter().write(json);

        return null;
    }
    public String showGoodsType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();
        List<GoodsType> goodsTypeList = goodsTypeService.findAllGoodsType();
        request.setAttribute("goodsTypeList", goodsTypeList);
        return "admin/showGoodsType.jsp";
    }


}
