<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>商品列表页</title>

</head>
<body>
<%@ include file="header.jsp" %>
<div class="panel panel-default" style="margin: 0 auto;width: 95%;">
    <div class="panel-heading">
        <h3 class="panel-title"><span class="glyphicon glyphicon-th-list"></span>&nbsp;&nbsp;商品列表</h3>
    </div>
    <div class="panel-body">
        <!--列表开始-->
        <div class="row ${pageInfo.pages<=0?"div1":"" }">
            <c:if test="${pageInfo.pages<=0 }">没有数据！！！</c:if>
            <div class="row" style="margin: 0 auto;">
                <c:forEach items="${pageInfo.list}" var="g" varStatus="i">
                    <div class="col-sm-3">
                        <div class="thumbnail">
                            <img src="${pageContext.request.contextPath}/goodsImgs/${g.picture}" width="180"
                                 height="180" alt="小米6"/>
                            <div class="caption">
                                <h4>商品名称<a
                                        href="${pageContext.request.contextPath}/goodsservlet?method=getGoodsById&id=${g.id}">${g.name}</a>
                                </h4>
                                <p>热销指数
                                    <c:forEach begin="1" end="${g.star}">
                                        <img src="image/star_red.gif" alt="star"/>
                                    </c:forEach>
                                </p>
                                <p>上架日期:${g.pubdate}</p>
                                <p style="color:orange">价格:${g.price}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
        </div>
    </div>

    <c:if test="${pageInfo.pages>0 }">
        <nav aria-label="..." class="text-center">
            <ul class="pagination">

                <c:if test="${pageInfo.isFirstPage }">
                    <li class="disabled"><span aria-hidden="true">«</span></li>
                </c:if>
                <c:if test="${pageInfo.pageNum>1 }">
                    <li><a href="${pageContext.request.contextPath }/goodsservlet?method=getGoodsListByTypeId&pageNum=${pageInfo.prePage}&pageSize=${pageInfo.pageSize}&typeId=${typeId}" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                </c:if>

                <c:forEach var="pn" begin="${pageInfo.navigateFirstPage}" end="${pageInfo.navigateLastPage}" step="1">
                    <c:if test="${pn==pageInfo.pageNum }">
                        <li class="active"><a href="#">${pn }<span class="sr-only">(current)</span></a></li>
                    </c:if>
                    <c:if test="${pn!=pageInfo.pageNum }">
                        <li ><a href="${pageContext.request.contextPath }/goodsservlet?method=getGoodsListByTypeId&pageNum=${pn }&pageSize=${pageInfo.pageSize}&typeId=${typeId}">${pn }</a></li>
                    </c:if>
                </c:forEach>

                <c:if test="${pageInfo.isLastPage }">
                    <li class="disabled"><span aria-hidden="true">»</span></li>
                </c:if>
                <c:if test="${pageInfo.pageNum<pageInfo.pages }">
                    <li><a href="${pageContext.request.contextPath }/goodsservlet?method=getGoodsListByTypeId&pageNum=${pageInfo.nextPage}&pageSize=${pageInfo.pageSize}&typeId=${typeId}" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                </c:if>

            </ul>
        </nav>
    </c:if>
</div>
</div>
    <!-- 底部 -->
    <%@ include file="footer.jsp" %>
</body>
</html>