<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<h2>
    <acme:message code="developer.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
    <tr>
        <th scope="row">
            <acme:message code="developer.dashboard.form.label.total-training-modules-with-update-moment"/>
        </th>
        <td>
            <acme:print value="${totalTrainingModulesWithUpdateMoment}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="developer.dashboard.form.label.total-training-sessions-with-link"/>
        </th>
        <td>
            <acme:print value="${totalTrainingSessionsWithLink}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="developer.dashboard.form.label.average-time-training-module"/>
        </th>
        <td>
            <acme:print value="${avgTimeTrainingModule}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="developer.dashboard.form.label.deviation-time-training-module"/>
        </th>
        <td>
            <acme:print value="${devTimeTrainingModule}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="developer.dashboard.form.label.minimum-time-training-module"/>
        </th>
        <td>
            <acme:print value="${minTimeTrainingModule}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="developer.dashboard.form.label.maximum-time-training-module"/>
        </th>
        <td>
            <acme:print value="${maxTimeTrainingModule}"/>
        </td>
    </tr>
       
  </table>

<acme:return/>