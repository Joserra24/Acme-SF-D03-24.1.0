<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-moment code="administrator.banner.form.label.instation-update-moment" path="instationUpdateMoment"/>
	<acme:input-moment code="administrator.banner.form.label.period-start" path="periodStart"/>
	<acme:input-moment code="administrator.banner.form.label.period-finish" path="periodFinish"/>
	<acme:input-url code="administrator.banner.form.label.link-picture" path="linkPicture"/>
	<acme:input-textbox code="administrator.banner.form.list.label.slogan" path="slogan"/>
	<acme:input-url code="administrator.banner.form.list.label.link-document" path="linkDocument"/>
	
	<jstl:choose>
		<jstl:when test="${_command == 'show' || _command == 'update'|| _command == 'delete'}">
			<acme:submit code="administrator.banner.form.button.update" action="/administrator/banner/update"/>
			<acme:submit code="administrator.banner.form.button.delete" action="/administrator/banner/delete"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="administrator.banner.form.button.create" action="/administrator/banner/create"/>
		</jstl:when>
	</jstl:choose>

</acme:form>