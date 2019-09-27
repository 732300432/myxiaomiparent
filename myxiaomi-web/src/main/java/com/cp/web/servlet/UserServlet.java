package com.cp.web.servlet;

import cn.dsna.util.images.ValidateCode;
import com.cp.pojo.Address;
import com.cp.pojo.User;
import com.cp.service.AddressService;
import com.cp.service.UserService;
import com.cp.utils.Base64Utils;
import com.cp.utils.RandomUtils;
import com.cp.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * cp 2019-09-10  15:26
 */
@WebServlet(value = "/userservlet")
public class UserServlet extends BaseServlet {
    UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");
    AddressService addressService = (AddressService) ContextLoader.getCurrentWebApplicationContext().getBean("addressServiceImpl");
    public String register(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;utf-8");
        String repassword = request.getParameter("repassword");

        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            if (StringUtils.isEmpty(user.getUsername())) {
                request.setAttribute("registerMsg", "用户名不能为空");
                return "register.jsp";
            }
            if (StringUtils.isEmpty(user.getPassword())) {
                request.setAttribute("registerMsg", "密码不能为空");
                return "register.jsp";
            }
            if (!user.getPassword().equals(repassword)) {
                request.setAttribute("registerMsg", "两次密码不一致");
                return "register.jsp";
            }
            if (StringUtils.isEmpty(user.getEmail())) {
                request.setAttribute("registerMsg", "邮箱不能为空");
                return "register.jsp";
            }
            if (StringUtils.isEmpty(user.getGender())) {
                request.setAttribute("registerMsg", "性别不能为空");
                return "register.jsp";
            }
            user.setFlag(0);//是否激活
            user.setRole(1);//普通用户
            user.setCode(RandomUtils.createActive());
            userService.register(user);
            return "redirect:registerSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "register.jsp";
    }

    public String checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        if (username == null || username.trim().length() == 0) {
            return null;
        }
        User user = userService.checkUsername(username);
        if (user != null) {
            response.getWriter().write("1");
        } else {
            response.getWriter().write("0");
        }
        return null;
    }

    public String createCode(HttpServletRequest request, HttpServletResponse response) {
        ValidateCode validateCode = new ValidateCode(100, 42, 4, 50);
        String pagecode = validateCode.getCode();
        System.out.println(pagecode);
        request.setAttribute("pagecode", pagecode);
        request.getSession().setAttribute("pagecode", pagecode);

        try {
            validateCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void checkCode(HttpServletRequest request, HttpServletResponse response) {
//        String pagecode = (String) request.getParameter("pagecode");
        String pagecode = (String) request.getSession().getAttribute("pagecode");
//        System.out.println(pagecode);
        String code = (String) request.getParameter("code");
//        System.out.println(code);
        if (!StringUtils.isEmpty(code)) {
            if (code.equalsIgnoreCase(pagecode)) {
                try {
                    response.getWriter().write("0");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    response.getWriter().write("1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public String userLogin(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username)) {
            request.setAttribute("msg", "用户名不能为空");
            return "login.jsp";
        }
        if (StringUtils.isEmpty(password)) {
            request.setAttribute("msg", "密码不能为空");
            return "login.jsp";
        }

        User user = userService.login(username, password);
//        System.out.println(MD5Utils.md5(password));
        if (user != null) {
            //判断是否激活 是否有权限
            if (user.getFlag() != 1) {
                request.setAttribute("msg", "您还没有激活，请激活后再登录");
                return "login.jsp";
            }
            if (user.getRole() != 1) {
                request.setAttribute("msg", "您没有权限登录");
                return "login.jsp";
            }
            //把用户信息存到session
            request.getSession().setAttribute("user", user);

            String auto = request.getParameter("auto");
            //把用户密码和用户名加密存到cookie
            if (auto != null) {
                String userinfo = Base64Utils.encode(username + "#" + password);
                Cookie cookie = new Cookie("userinfo", userinfo);
                cookie.setMaxAge(60 * 60 * 24 * 14);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
                return "redirect:index.jsp";
        } else {
            request.setAttribute("msg", "用户名或者密码错误");
            return "login.jsp";
        }


    }

    public String userLogout(HttpServletRequest request, HttpServletResponse response){
        //删除session
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();

        Cookie cookie = new Cookie("userinfo","" );
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:index.jsp";
    }
    public String getAddress(HttpServletRequest request, HttpServletResponse response){

        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:login.jsp";
        }
        List<Address> addList = addressService.findAddressByUid(user.getId());
        request.setAttribute("addList",addList );
        return "self_info.jsp";
    }
}
