<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${not empty requestScope.errorMessage}">
    <div style="color:red; margin-bottom:10px; border:none;">${requestScope.errorMessage}</div>
</c:if>
