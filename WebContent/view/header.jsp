<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


	<main> <header>
		<div class="logo">레스고!</div>
		<ul class="menu"
			style="list-style: none; display: flex; padding: 0; margin: 0;">
			<li style="margin-right: 20px;"><a href="controller">홈</a></li>
			<li style="margin-right: 20px;"><a href="controller?cmd=">플레이스 조회</a></li>
			<li style="margin-right: 20px;"><a href="controller?cmd=postScheduleListUI">일정게시판</a></li>
			<li style="margin-right: 20px;"><a href="controller?cmd=myScheduleListUI">내일정</a></li>
			<c:if test="${loginOK == null}">
			<li><a href="controller?cmd=loginUI">로그인</a></li>
			</c:if>	
			<c:if test="${loginOK != null}">
			<li>${info.name}<li>
			<li><a href="controller?cmd=logoutAction">로그아웃</a></li>
			</c:if>	
			
		</ul>
	</header>
</html>