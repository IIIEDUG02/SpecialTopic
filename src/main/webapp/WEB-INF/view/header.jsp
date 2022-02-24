<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="net.ddns.iiiedug02.model.services.MemberService" %>
<%@ page import="net.ddns.iiiedug02.model.beans.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="net.ddns.iiiedug02.controller.listeners.OnlineUsers" %>
<% 
String username = (String)request.getSession().getAttribute("username");
%>
Hi <%= username %>(線上人數: <%= OnlineUsers.count %> 人)<input type="button" value="登出" onclick="javascript:location.href='/SpecialTopic/Logout'"><hr>
