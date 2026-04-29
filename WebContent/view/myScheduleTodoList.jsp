<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
</head>
<body>
<jsp:include page="mySchduleSideBar.jsp" />
<jsp:include page="header.jsp" />


<main>
    <div class="content-container">
        <jsp:include page="MyScheduleRoute.jsp" />
        <div class="divider"></div>
        <div class="content-right">
            <div class="memo">
                <div>
                    <h3>To-Do</h3>
                </div>
                <form method="post" action="controller">
                    <input type="hidden" name="cmd" value="myScheduleTodoListAction">
                    <textarea name="todoDetail" class="detail-textarea"
                        placeholder="1. 금원 수원 수영장 - 수영복 챙기기">${todoDetail}</textarea>
                    <button type="submit" class="save-btn">저장하기</button>
                </form>
                <c:if test="${TodoDetailResult == true}">
                    <p>저장되었습니다.</p>
                </c:if>
                <c:if test="${TodoDetailResult == false}">
                    <p>저장에 실패했습니다.</p>
                </c:if>
            </div>
        </div>
    </div>
</main>



</body>
</html>
