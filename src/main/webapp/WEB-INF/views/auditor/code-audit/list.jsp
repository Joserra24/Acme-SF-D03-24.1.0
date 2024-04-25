<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="auditor.code-audit.list.label.code" path="code" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.execution" path="execution" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.type" path="type" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.corrective-actions" path="correctiveActions" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.mark" path="mark" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.optional-link" path="optionalLink" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.project" path="project" width="10%"/>
	<acme:list-column code="auditor.code-audit.list.label.draft-mode" path="draftMode" width="10%"/>
</acme:list>

<acme:button code="auditor.code-audit.list.button.create" action="/auditor/code-audit/create"/>