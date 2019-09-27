package com.cp.web.servlet;

import com.alibaba.fastjson.JSON;
import com.cp.pojo.GoodsType;
import com.cp.service.GoodsTypeService;
import org.springframework.web.context.ContextLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * cp 2019-09-11  11:59
 */
@WebServlet(value = "/goodstype")
public class GoodsTypeServlet extends BaseServlet{
    GoodsTypeService goodsService = (GoodsTypeService) ContextLoader.getCurrentWebApplicationContext().getBean("goodsTypeServiceImpl");
    public String goodsTypeAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        //只获取level为1的商品种类
        List<GoodsType> goodsTypes=goodsService.getGoodsType(1);
        String json = JSON.toJSONString(goodsTypes);
        response.getWriter().write(json);
        return null;
    }
}
