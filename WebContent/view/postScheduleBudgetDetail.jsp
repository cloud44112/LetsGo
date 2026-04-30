<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="postScheduleDetailSideBar.jsp" />
<jsp:include page="header.jsp" />
<main>
    <div class="content-container">
        <jsp:include page="MyScheduleRoute.jsp" />
        <div class="divider"></div>
        <div class="content-right">
            <div class="memo">
                <div>
                    <h3>예산관리</h3>
                </div>
                <form method="post" action="controller">
                    <input type="hidden" name="cmd" value="myScheduleBudgetDetailAction">
                    <textarea name="budgetDetail" class="detail-textarea"
                        placeholder="1. 금원 수원 수영장 - 입장권 5000원">${budgetDetail}</textarea>
                    <button type="submit" class="save-btn">저장하기</button>
                </form>
                <c:if test="${BudgetDetailResult == true}">
                    <p>저장되었습니다.</p>
                </c:if>
                <c:if test="${BudgetDetailResult == false}">
                    <p>저장에 실패했습니다.</p>
                </c:if>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script>
    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });
</script>

</body>
</html>