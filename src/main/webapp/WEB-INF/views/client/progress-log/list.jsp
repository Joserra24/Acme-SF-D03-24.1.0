<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="client.progress-log.list.label.record-id" path="recordId" width="10%"/>
	<acme:list-column code="client.progress-log.list.label.completeness" path="completeness" width="10%"/>
	<acme:list-column code="client.progress-log.list.label.comment" path="comment" width="10%"/>
	<acme:list-column code="client.progress-log.list.label.registration-moment" path="registrationMoment" width="10%"/>	
	<acme:list-column code="client.progress-log.list.label.responsible-person" path="responsiblePerson" width="10%"/>
	<acme:list-column code="client.progress-log.list.label.draft-mode" path="draftMode" width="10%"/>
	<acme:list-column code="client.progress-log.list.label.contract" path="contract" width="10%"/>
</acme:list>

<acme:button code="client.progress-log.list.button.create" action="/client/progress-log/create"/>