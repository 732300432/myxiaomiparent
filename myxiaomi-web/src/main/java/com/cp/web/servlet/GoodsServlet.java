package com.cp.web.servlet;

import com.cp.pojo.Goods;
import com.cp.service.GoodsService;
import com.cp.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * cp 2019-09-11  13:12
 */
@WebServlet(value = "/goodsservlet")
public class GoodsServlet extends BaseServlet {
    GoodsService goodsService = (GoodsService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsServiceImpl");

    public String getGoodsListByTypeId(HttpServletRequest request, HttpServletResponse response) {
        String _pageNum = request.getParameter("pageNum");
        String _pageSize = request.getParameter("pageSize");
        String typeId = request.getParameter("typeId");
        System.out.println(_pageNum + "   " + _pageSize + "  " + typeId);
        String condition = "";
        if (!StringUtils.isEmpty(typeId)) {
            condition = "typeid=" + typeId;
        }
        int pageNum = 1;
        int pageSize = 8;
        if (!StringUtils.isEmpty(_pageNum)) {
            if (Integer.parseInt(_pageNum) < 1) {
                pageNum = 1;

            } else {
                pageNum = Integer.parseInt(_pageNum);
            }
        }
        if (!StringUtils.isEmpty(_pageSize)) {
            if (Integer.parseInt(_pageSize) < 1) {
                pageSize = 1;
            } else {
                pageSize = Integer.parseInt(_pageSize);
            }
        }
        try {
            PageHelper.startPage(pageNum,pageSize );
            List<Goods> goodsList = goodsService.findPageByWhere(condition);
            PageInfo<Goods> pageInfo = new PageInfo<>(goodsList,5);
            request.setAttribute("pageInfo", pageInfo);
            return "goodsList.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:index.jsp";
        }
    }

    public String getGoodsById(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        try {
            if (!StringUtils.isEmpty(id)) {
                Goods goods = goodsService.findGoodsById(Integer.parseInt(id));
                request.setAttribute("goods",goods );
                return "goodsDetail.jsp";
            }else {
                return "redirect:index.jsp";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "redirect:index.jsp";
        }

    }
}
