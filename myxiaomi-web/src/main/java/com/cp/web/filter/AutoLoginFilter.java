package com.cp.web.filter;

import com.cp.pojo.User;
import com.cp.service.UserService;
import com.cp.utils.Base64Utils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cp 2019-09-11  10:04
 */
@WebFilter(filterName = "AutoLoginFilter",value = "/index.jsp")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        User user = (User) request.getSession().getAttribute("user");
        if(user!=null){
            chain.doFilter(req, resp);
            return;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userinfo")){
                    String value = cookie.getValue();
                    //解密
                    String userinfo = Base64Utils.decode(value);
                    String[] split = userinfo.split("#");
                    //自动登录
                    UserService userService = (UserService) ContextLoader.getCurrentWebApplicationContext().getBean("userServiceImpl");
                    User user1 = userService.login(split[0], split[1]);
                    if(user1!=null){
                        if(user1.getFlag()==1){
                            request.getSession().setAttribute("user",user1 );
                            chain.doFilter(req, resp);
                            return;
                        }
                    }else {
                        //删除cookie
                        Cookie cookie1 = new Cookie("userinfo","" );
                        cookie1.setMaxAge(0);
                        cookie1.setPath("/");
                        response.addCookie(cookie1);

                    }
                }
            }
        }

        chain.doFilter(req, resp);
        return;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
