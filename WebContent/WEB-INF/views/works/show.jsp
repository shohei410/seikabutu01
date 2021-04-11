<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

<c:choose>
<c:when test="${work != null }">
  <h2>事由　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>社員番号</th>
                             <td><c:out value="${work.employee.id}" /></td>
                        </tr>

                        <tr>
                            <th>氏名</th>
                           <td><c:out value="${work.employee.name}" /></td>
                        </tr>

                        <tr>
                            <th>勤務予定時間</th>
                             <td>
                           <c:choose>
                            <c:when test="${work.plan_flag == 0}">日勤:9:00~18:00</c:when>
                            <c:when test="${work.plan_flag == 1}">夜勤:11:00~20:00</c:when>
                            <c:when test="${work.plan_flag == 2}"><p>その他</p></c:when>
                        </c:choose>
                        </td>
                        </tr>

                        <tr>
                            <th>本日の予定</th>
                            <td>
                               <pre><c:out value="${work.content}" /></pre>
                            </td>
                        </tr>
                        <tr>
                            <th>事由</th>
                             <td>
                            </td>
                        </tr>
                        <tr>
                            <th>出社時間</th>
                            <td> <fmt:formatDate value='${work.syussya_at}' pattern='yyyy/MM/dd HH:mm:ss' /></td>
                        </tr>
                        <tr>
                            <th>退社時間</th>
                            <td>
                             <fmt:formatDate value='${work.taisya_at}' pattern='yyyy/MM/dd HH:mm:ss' /></td>
                        </tr>
                    </tbody>
                </table>

                <c:if test="${sessionScope.login_employee.id == work.employee.id}">
                    <p><a href="<c:url value="/works/edit?id=${work.id}" />">修正する</a></p>
                </c:if>
    </c:when>

    <c:otherwise>
    <h2>お探しのデータは見つかりませんでした。</h2>
    </c:otherwise>
    </c:choose>

       <p><a href="<c:url value="/works/index" />">一覧に戻る</a></p>
    </c:param>
</c:import>