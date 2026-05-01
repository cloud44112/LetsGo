<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/LetsGo/view/css/mySchedule.css">
</head>
<body>
<jsp:include page="postScheduleDetailSideBar.jsp" />
<jsp:include page="header.jsp" />
<main>
    <div class="content-container">
        <jsp:include page="postScheduleRoute.jsp" />
        <div class="divider"></div>
        <div class="content-right">
            <div class="memo">
                <div>
                    <h3>To-Do</h3>
                </div>
                <form method="post" action="controller">
                    <span id="todoDetail" class="detail-textarea">${todoDetail}</span>
                </form>
            </div>
        </div>
    </div>
</main>

</body>
</html>