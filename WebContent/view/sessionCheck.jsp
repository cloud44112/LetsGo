<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${loginOK eq null}">
	<script type = "text/javascript" src = "js/login_message.js">
	</script>
</c:if>


<a href="controller?cmd=logoutAction">logout</a> <br/>