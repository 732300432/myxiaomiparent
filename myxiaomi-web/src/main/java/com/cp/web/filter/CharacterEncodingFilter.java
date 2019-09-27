package com.cp.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * cp 2019-09-10  17:23
 */
@WebFilter(filterName = "CharacterEncodingFilter",value = "/*")
public class CharacterEncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        chain.doFilter(req, resp);
        //不加return  会重新加载一次
        return;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
