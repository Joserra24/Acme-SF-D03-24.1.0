<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="any.claim.list.label.code" path="code" width="33%"/>
	<acme:list-column code="any.claim.list.label.instantiation-moment" path="instantiationMoment" width="33%"/>
	<acme:list-column code="any.claim.list.label.heading" path="heading" width="33%"/>
	<acme:list-column code="any.claim.list.label.description" path="description" width="33%"/>
	<acme:list-column code="any.claim.list.label.department" path="department" width="33%"/>
	<acme:list-column code="any.claim.list.label.email" path="email" width="33%"/>
	<acme:list-column code="any.claim.list.label.link" path="link" width="33%"/>
ç</acme:list>

<acme:button code="any.claim.list.button.create" action="/any/claim/create"/>