<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.contract.list.label.code" path="code" width="10%"/>
	<acme:list-column code="client.contract.list.label.instantiation-moment" path="instantiationMoment" width="10%"/>
	<acme:list-column code="client.contract.list.label.provider-name" path="providerName" width="10%"/>	
	<acme:list-column code="client.contract.list.label.customer-name" path="customerName" width="10%"/>
	<acme:list-column code="client.contract.list.label.goals" path="goals" width="10%"/>
	<acme:list-column code="client.contract.list.label.budget" path="budget" width="10%"/>
	<acme:list-column code="client.contract.list.label.project" path="project" width="10%"/>
	<acme:list-column code="client.contract.list.label.draft-mode" path="draftMode" width="10%"/>
</acme:list>

<acme:button code="client.contract.list.button.create" action="/client/contract/create"/>