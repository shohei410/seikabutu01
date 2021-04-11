<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<c:import url="../layout/app.jsp">
    <c:param name="content">

        <h2><a href='../works/index'>出社確認</a></h2>

        <form method="POST" action="<c:url value='/works/create' />">
          <c:import url="_form.jsp" />
        </form>
         <button type="cancel" onclick="location.href='../employees/index'">キャンセル</button>

    </c:param>
</c:import>