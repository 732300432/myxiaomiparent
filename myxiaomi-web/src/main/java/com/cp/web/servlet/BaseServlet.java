package com.cp.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * cp 2019-09-10  15:16
 */
public class BaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String methodName = request.getParameter("method");
        try {
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            String url = (String) method.invoke(this,request ,response);
            if(url!=null&&url.trim().length()!=0){
                if(url.startsWith("redirect:")){
                    response.sendRedirect(request.getContextPath()+"\\"+url.split(":")[1]);
                }else {
                    request.getRequestDispatcher("\\"+url).forward(request,response );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response );
    }
}
