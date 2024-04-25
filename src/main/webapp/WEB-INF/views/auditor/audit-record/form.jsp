<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="auditor.audit-record.form.label.code" path="code"/>
	<acme:input-moment code="auditor.audit-record.form.label.initial-period" path="initialPeriod"/>	
	<acme:input-moment code="auditor.audit-record.form.label.final-period" path="finalPeriod"/>	
	<acme:input-select code="auditor.audit-record.form.label.mark" path="mark" choices="${mark}"/>	
	<acme:input-url code="auditor.audit-record.form.label.optional-link" path="optionalLink"/>	
	<acme:input-select code="auditor.audit-record.form.label.code-audit" path="codeAudit" choices="${codeAudits}"/>
	<acme:input-checkbox code="auditor.audit-record.form.label.draft-mode" path="draftMode" readonly="true" />	
		
		
	<jstl:choose>	 
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:submit code="auditor.audit-record.form.button.update" action="/auditor/audit-record/update"/>
			<acme:submit code="auditor.audit-record.form.button.delete" action="/auditor/audit-record/delete"/>
			<acme:submit code="auditor.audit-record.form.button.publish" action="/auditor/audit-record/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="auditor.audit-record.form.button.create" action="/auditor/audit-record/create"/>
		</jstl:when>			
	</jstl:choose>
</acme:form>