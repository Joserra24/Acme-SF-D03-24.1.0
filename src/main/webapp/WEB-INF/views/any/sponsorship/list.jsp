<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.sponsorship.list.label.code" path="code" width="15%"/>
	<acme:list-column code="any.sponsorship.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="any.sponsorship.list.label.durationStart" path="durationStart" width="20%"/>
	<acme:list-column code="any.sponsorship.list.label.durationEnd" path="durationEnd" width="20%"/>
	<acme:list-column code="any.sponsorship.list.label.amount" path="amount" width="15%"/>
	<acme:list-column code="any.sponsorship.list.label.projectType" path="projectType" width="10%"/>
</acme:list>