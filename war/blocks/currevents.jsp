<%@ page import="java.util.List"%>
<%@ page import="java.util.Stack"%>
<%@ page import="net.rwchess.site.data.DAO"%>
<%@ page import="net.rwchess.site.data.LatestEvents"%>

<div id="left">
<div class="leftitem">

<jsp:include page="/WEB-INF/jsp/wiki/reg.jsp"></jsp:include>

<ul>
	<h2>Current Events</h2>
	<ul>
		<li><a style="font-size: 15px;"
			href="/t41">T41</a></li>
	</ul>
	<ul>
		<li><a style="font-size: 15px;"
			href="/ladder">RW Ladder</a></li>
	</ul>
	</ul>
<br/>
<h2>Old events</h2>
<ul>
		<li><a style="font-size: 15px;"
			href="/swiss">RW Swiss</a></li>
	</ul><br/>
<h2>Latest activities</h2>
<ul>
<% LatestEvents ev = DAO.getEvents();   
   if (ev != null) {
	   for (String s: ev.getStack()) {
		   out.println("<li>"+s+"</li><br/>");
	   }
   }
%>
</ul>
</div>
</div>
<div id="right">
