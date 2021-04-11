<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">

       <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2><a href='../works/new'>勤怠ログ一覧</a></h2>
               <table id="work_list">
            <tbody>
                <tr>
                    <th class="work_id">社員番号</th>
                    <th class="work_name">氏名</th>
                    <th class="work_plan">勤務予定時間</th>
                    <th class="work_syussya">出社時間〜</th>
                    <th class="work_taisya">退社時間</th>
                    <th class="work_action">操作</th>
                </tr>
                 <c:forEach var="work" items="${works}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td class="work_id"><c:out value="${work.employee.id}" /></td>
                        <td class="work_name"><c:out value="${work.employee.name}" /></td>
                        <td>
                           <c:choose>
                            <c:when test="${work.plan_flag == 0}">日勤:9:00~18:00</c:when>
                            <c:when test="${work.plan_flag == 1}">夜勤:11:00~20:00</c:when>
                            <c:when test="${work.plan_flag == 2}"><p>その他</p></c:when>
                        </c:choose>
                        </td>
                        <td class="work_syussya"><p><fmt:formatDate value='${work.syussya_at}' pattern='yyyy/MM/dd HH:mm:ss' />~</p></td>
                        <td class="work_taisya"> <fmt:formatDate value='${work.taisya_at}' pattern='yyyy/MM/dd' /></td>

                        <td class="work_action"><a href="<c:url value='/works/show?id=${work.id}' />">詳細を見る</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${works_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((works_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/works/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p><a href="<c:url value='/reports/new' />">新規日報の登録</a></p>

    </c:param>
</c:import>