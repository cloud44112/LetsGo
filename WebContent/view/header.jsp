<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<header class="site-header">
		<style type="text/css">
			header.site-header {
				box-sizing: border-box;
				width: 100%;
				height: auto;
				min-height: 180px;
				display: flex;
				justify-content: space-between;
				align-items: center;
				padding: 10px 40px;
				border-bottom: 4px solid #ff7a00;
			}
			.logo { display: flex; align-items: center; flex-shrink: 0; }
			.logo .site-logo { height: 200px; width: auto; display: block; }
			.logo a { display: inline-flex; align-items: center; text-decoration: none; line-height: 0; }
			.menu {
				align-items: center;
				margin-left: auto;
				gap: 12px;
				font-size: 16px;
			}
			.menu li {
				margin: 0;
			
			}
			.menu a {
				display: inline-block;
				padding: 10px 18px;
				border: 1px solid #ff7a00;
				border-radius: 24px;
			
				
				text-decoration: none;
				font-weight: 700;
				transition: background 0.2s ease, color 0.2s ease, transform 0.2s ease;
			}
			.menu a:hover {
				background: #ff7a00;
				
				transform: translateY(-2px);
			}
			.menu .user-name {
				padding: 10px 14px;
				color: #534537;
				font-weight: 700;
			}
		</style>
		<div class="logo">
			<a href="${pageContext.request.contextPath}/controller" title="Let's GO! 홈">
				<img src="${pageContext.request.contextPath}/view/img/logo.png" alt="Let's GO!" class="site-logo" />
			</a>
		</div>
		<ul class="menu"
			style="list-style: none; display: flex; padding: 0; margin: 0;">
			<li><a href="controller">홈</a></li>
			<li><a href="controller?cmd=leisureUI">플레이스 조회</a></li>
			<li><a href="controller?cmd=postScheduleListUI">일정게시판</a></li>
			<li><a href="controller?cmd=myScheduleListUI">내일정</a></li>
			<c:if test="${loginOK == null}">
			<li><a href="controller?cmd=loginUI">로그인</a></li>
			</c:if>	
			<c:if test="${loginOK != null}">
			<li class="user-name">${empty info.name ? loginOK : info.name} 님</li>
			<li><a href="controller?cmd=logoutAction">로그아웃</a></li>
			</c:if>	
			
		</ul>
	</header>