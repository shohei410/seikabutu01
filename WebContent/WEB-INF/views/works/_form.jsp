<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>

  <label for="plan_flag">勤務予定時間</label><br /> <select name="plan_flag">
                <option value="0"
                    <c:if test="${work.plan_flag == 0}"> selected</c:if>>日勤:9:00~18:00</option>
                <option value="1"
                    <c:if test="${work.plan_flag == 1}"> selected</c:if>>夜勤:11:00~20:00</option>
                <option value="2"
                    <c:if test="${work.plan_flag == 2}"> selected</c:if>>その他</option>
      </select> <br />


            <br /> <label for="attend_flag">出社確認</label><br />

            <!-- <select name="attend_flag">-->
            <!--  <option value="0"<c:if test="${work.attend_flag == 0}"> selected</c:if>>出社</option>-->
             <!-- <option value="1"<c:if test="${work.attend_flag == 1}"> selected</c:if>>退社</option>-->
      <!-- 【例】<input type="radio" name="fruit" value="apple">りんご</input> -->
                <input type="radio" name="attends" value="0" id="arrive">出社<br>
                <input type="radio" name="attends" value="1" id="Leave">退社




            <br />
            <br /> <label for="content">本日の予定</label><br />
            <textarea name="content" rows="10" cols="30">${work.content}</textarea>
            <br />

            <br /> <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">登録</button>



