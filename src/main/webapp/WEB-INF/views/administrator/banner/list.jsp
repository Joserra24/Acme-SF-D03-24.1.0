<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="administrator.banner.list.label.period-start" path="periodStart"/>
	<acme:list-column code="administrator.banner.list.label.period-finish" path="periodFinish"/>
	<acme:list-column code="administrator.banner.list.label.slogan" path="slogan"/>
</acme:list>

<jstl:if test="${_command == 'list'}">
	<acme:button code="administrator.banner.list.button.create" action="/administrator/banner/create"/>
</jstl:if>