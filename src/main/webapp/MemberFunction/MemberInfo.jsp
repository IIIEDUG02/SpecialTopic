<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.ddns.iiiedug02.model.services.MemberService"%>
<%@ page import="net.ddns.iiiedug02.model.beans.MemberBean"%>
<%@ page import="org.hibernate.Session"%>
<%@ page import="org.hibernate.SessionFactory"%>
<%@ page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%
WebApplicationContext context =
    WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
MemberService ms = context.getBean("memberService", MemberService.class);

MemberBean queryBean;
MemberBean loginBean = (MemberBean) request.getSession().getAttribute("loginBean");
// 指定修改的Bean
String username = request.getParameter("username");
String inputType;
if (username == null) {
  queryBean = loginBean;
  inputType = "hidden";
} else {
  queryBean = ms.selectByUsername(username);
  inputType = "text";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/header.jsp" flush="true" />
	<form method='get' action='/SpecialTopic/MemberFunction/MemberUpdate'>
		<!-- MemberBean -->
		帳號:
		<%=queryBean.getUsername()%>
		<input type='hidden' name='username'
			value='<%=queryBean.getUsername()%>'><br> 密碼: <input
			type='text' name='password' value='<%=queryBean.getPassword()%>'><br>
		<!-- 非admin不能設定狀態 -->
		激活狀態: <input type='<%=inputType%>' name='activated'
			value='<%=queryBean.getActivated()%>'><br>
		<!-- 非admin不能設定權限 -->
		權限: <input type='<%=inputType%>' name='auth'
			value='<%=queryBean.getAuth()%>'><br>
		<!-- MemberDetailBean -->
		姓名: <input type='text' name='fullname'
			value='<%=queryBean.getMemberDetail().getFullname()%>'><br>
		地址: <input type='text' name='address'
			value='<%=queryBean.getMemberDetail().getAddress()%>'><br>
		電話: <input type='text' name='phone'
			value='<%=queryBean.getMemberDetail().getPhone()%>'><br>
		職業: <input type='text' name='job'
			value='<%=queryBean.getMemberDetail().getJob()%>'><br>
		生日: <input type='date' name='birthday'
			value='<%=queryBean.getMemberDetail().getBirthday()%>'><br>
		Email: <input type='text' name='email'
			value='<%=queryBean.getMemberDetail().getEmail()%>'><br>
		<!-- 隱藏uid -->
		<input type='hidden' name='uid' value='%s'> <input
			type='submit' value='確認'>
			<input type="<%= username != null ? "button" : "hidden" %>" onclick="javascript:history.back()" value="放棄" />

	</form>


</body>
</html>