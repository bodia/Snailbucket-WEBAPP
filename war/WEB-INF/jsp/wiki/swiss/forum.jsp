<%@ page import="net.rwchess.wiki.*"%>
<%@ page import="net.rwchess.site.data.RWMember"%>

<% WikiPage wikiPage = (WikiPage) request.getAttribute("pageRequested"); %>

<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>

<head>
  <title>RWarriors Wiki - <%=wikiPage.getName() %></title>
  <meta name="robots" content="noindex,nofollow" />
  <link href="/static/jspwiki.css" type="text/css" rel="stylesheet" />

</head>
<body>

<div id="wiki-page">
<div id="wiki-navigation">
	<div id="logo">
	
	<jsp:include page="/WEB-INF/jsp/wiki/logoArea.jsp"></jsp:include>
	</div>
	<br />
	
	<div id="nav-menu">
	<jsp:include page="/WEB-INF/jsp/wiki/leftMenu.jsp"></jsp:include>
	</div>
	
	<div id="nav-search">

	<form method="post" action="/wiki/en/Special:Search">
	<input type="text" name="text" size="20" value="" />
	<br />
	<input type="submit" name="search" value='Search'/>
	<input type="submit" name="jumpto" value='Jump to'/>
	</form>
	</div>
</div>
<div id="wiki-content">
	

<jsp:include page="/WEB-INF/jsp/wiki/reg.jsp"></jsp:include>


<div class="clear"></div>

	
<div id="tab-menu">
	
	
</div>
<div class="clear"></div>

	<div id="contents" >
	<h1 id="contents-header"><%=wikiPage.getName() %></h1>
	

<%=wikiPage.getHtmlText().getValue() %>

<% RWMember user = (RWMember) pageContext.getSession().getAttribute("user"); 
   if (user != null) {  
%>	
<fieldset>

<form name="form" method="post" name="editform" action="/wiki/<%=wikiPage.getName() %>">
<input type="hidden" name="pageName" value="<%=wikiPage.getName() %>" />



<p>
<textarea id="topicContents" name="contents" rows="25" cols="10" accesskey=",">Enter the comment here</textarea>
</p>
<p>

<input type="submit" name="save" value="Post"  accesskey="s" />


</p>



</form>

</fieldset>
<% } %>



	<br />
	</div>
</div>

<jsp:include page="/WEB-INF/jsp/wiki/footer.jsp"></jsp:include>

</div>


</body>
</html>
