
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>				
	<jstl:choose>
		<jstl:when test="${_command == 'show'}">
			<acme:submit code="manager.project-user-story.form.button.delete" action="/manager/project-user-story/delete?id=${id}"/>
		</jstl:when>		

		<jstl:when test="${_command == 'create'}">
			<acme:input-select code="manager.project-user-story.form.label.projects" path="projects" choices="${projects}"/>	
			<acme:input-select code="manager.project-user-story.form.label.user-stories" path="userStories" choices="${userStories}"/>	
			<acme:submit code="manager.project-user-story.form.button.create" action="/manager/project-user-story/create?masterId=${masterId}"/>
		</jstl:when>		
	</jstl:choose>	

</acme:form>