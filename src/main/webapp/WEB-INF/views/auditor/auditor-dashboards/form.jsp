<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<h2>
    <acme:message code="auditor.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
    <tr>
        <th scope="row">
            <acme:message code="auditor.dashboard.form.label.total-code-audits-static"/>
        </th>
        <td>
            <acme:print value="${totalCodeAuditsStatic}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="auditor.dashboard.form.label.total-code-audits-dynamic"/>
        </th>
        <td>
            <acme:print value="${totalCodeAuditsDynamic}"/>
        </td>
    </tr>
  </table>

<acme:return/>