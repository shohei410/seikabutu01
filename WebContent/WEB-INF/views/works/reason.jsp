<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h4><b>勤務時間登録の内容に不備があります。下記に沿って事由をお答えください。</b></h4>
        <br></br>

<b><label for="name">事由</label></b><br>
<form action="attend" method="post">
<input type="radio" name="attends" value="1" id="arrive">遅刻<br>
<input type="radio" name="attends" value="2" id="Leave">打刻忘れ<br>
<input type="radio" name="attends" value="3" id="arrive">時間外勤務<br>
<input type="radio" name="attends" value="4" id="Leave">その他<br>
</form>
<br /><br />

<label for="password">詳細</label><br>
<textarea name="content" rows="5" cols="40"></textarea>
<br /><br />

<br /><br />


<input type="hidden" name="_token" value="${_token}" />
<button type="submit">登録</button>
<button type="cancel" onclick="location.href='../employees/index'">キャンセル</button>




    </c:param>
</c:import>