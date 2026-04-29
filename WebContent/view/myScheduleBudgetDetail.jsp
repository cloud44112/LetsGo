<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <pre>${budgetDetail}</pre>
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
