<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:list>
	<acme:list-column code="manager.user-story.list.label.title" path="title" width="10%"/>
	<acme:list-column code="manager.user-story.list.label.description" path="description" width="10%"/>
	<acme:list-column code="manager.user-story.list.label.estimated-cost" path="estimatedCost" width="10%"/>
	<acme:list-column code="manager.user-story.list.label.acceptance-criteria" path="acceptanceCriteria" width="10%"/>
	<acme:list-column code="manager.user-story.list.label.priority" path="priority" width="10%"/>
	<acme:list-column code="manager.user-story.list.label.link" path="link" width="10%"/>
	<acme:list-column code="manager.user-story.list.label.draft-mode" path="draftMode" width="10%"/>
</acme:list>

<acme:button code="manager.user-story.list.button.create" action="/manager/user-story/create"/>