package com.cp.web.servlet;

import com.cp.pojo.Address;
import com.cp.pojo.User;
import com.cp.service.AddressService;
import com.cp.utils.StringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * cp 2019-09-12  9:59
 */
@WebServlet(value = "/addressservlet")
public class AddressServlet extends BaseServlet {
    AddressService addressService = (AddressService) ContextLoader.getCurrentWebApplicationContext().getBean("addressServiceImpl");

    public String getAddress(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }
        List<Address> addList = addressService.findAddressByUid(user.getId());
        request.setAttribute("addList", addList);
        return "self_info.jsp";
    }

    public String addAddress(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String detail = request.getParameter("detail");
        if (StringUtils.isEmpty(name)) {
            request.setAttribute("msg", "收货人不能为空");
            return getAddress(request, response);
        }
        if (StringUtils.isEmpty(phone)) {
            request.setAttribute("msg", "电话不能为空");
            return getAddress(request, response);
        }
        if (StringUtils.isEmpty(detail)) {
            request.setAttribute("msg", "详细地址不能为空");
            return getAddress(request, response);
        }


        //级别默认为0
        Address address = new Address(null, detail, name, phone, user.getId(), 0);
        addressService.addAddress(address);

        return getAddress(request, response);
    }

    public String updateDefaultAddress(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }
        String _aid = request.getParameter("id");
        Integer aid = null;
        if (!StringUtils.isEmpty(_aid)) {
            aid = Integer.parseInt(_aid);
        }
        addressService.updateDefaultAdd(user.getId(), aid);

        return getAddress(request, response);
    }

    public String deleteAddress(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }
        String id = request.getParameter("id");

        if (!StringUtils.isEmpty(id)) {
            Integer aid = Integer.parseInt(id);
            addressService.deleteAddressById(aid);
        }
        return getAddress(request, response);
    }

    public String updateAddress(HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:login.jsp";
        }
        Address address = new Address();
        BeanUtils.populate(address, request.getParameterMap());
        address.setUid(user.getId());
        if(StringUtils.isEmpty(address.getName())){
            request.setAttribute("msg","收件人不能为空" );
            return getAddress(request, response);
        }
        if(StringUtils.isEmpty(address.getPhone())){
            request.setAttribute("msg","收件人电话不能为空" );
            return getAddress(request, response);
        }
        if(StringUtils.isEmpty(address.getDetail())){
            request.setAttribute("msg","地址不能为空" );
            return getAddress(request, response);
        }

        addressService.updateAddress(address);
        return getAddress(request, response);
    }
}

