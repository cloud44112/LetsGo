<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="layout-wrapper">
<jsp:include page="postScheduleDetailSideBar.jsp" />

<main>
    <div class="content-container">
        <jsp:include page="postScheduleRoute.jsp" />
        <div class="divider"></div>
        <div class="content-right">
            <div class="memo">
                <div>
                    <h3>예산관리</h3>
                </div>
                <form method="post" action="controller">
                    <span id="budgetDetail" class="detail-textarea">${budgetDetail}</span>
                </form>

            </div>
        </div>
    </div>
</main>
</div>

<script src="https://cdn.jsdelivr.net/npm/sortablejs@1.15.0/Sortable.min.js"></script>
<script>
    new Sortable(document.getElementById('sortableList'), {
        animation: 150,
        ghostClass: 'sortable-ghost'
    });
</script>

</body>
</html>