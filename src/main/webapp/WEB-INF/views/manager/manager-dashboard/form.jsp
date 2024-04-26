<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>
<h2>
    <acme:message code="manager.dashboard.form.title.general-indicators"/>
</h2>

<table class="table table-sm">
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.total-must-user-story"/>
        </th>
        <td>
            <acme:print value="${totalMustUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.total-should-user-story"/>
        </th>
        <td>
            <acme:print value="${totalShouldUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.total-could-user-story"/>
        </th>
        <td>
            <acme:print value="${totalCouldUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.total-wont-user-story"/>
        </th>
        <td>
            <acme:print value="${totalWontUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.average-estimated-cost-user-story"/>
        </th>
        <td>
            <acme:print value="${avgEstimatedCostUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.deviation-estimated-cost-user-story"/>
        </th>
        <td>
            <acme:print value="${devEstimatedCostUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.minimum-estimated-cost-user-story"/>
        </th>
        <td>
            <acme:print value="${minEstimatedCostUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.maximum-estimated-cost-user-story"/>
        </th>
        <td>
            <acme:print value="${maxEstimatedCostUserStory}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.average-cost-project"/>
        </th>
        <td>
            <acme:print value="${avgCostProject}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.deviation-cost-project"/>
        </th>
        <td>
            <acme:print value="${devCostProject}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.minimum-cost-project"/>
        </th>
        <td>
            <acme:print value="${minCostProject}"/>
        </td>
    </tr>
    <tr>
        <th scope="row">
            <acme:message code="manager.dashboard.form.label.maximum-cost-project"/>
        </th>
        <td>
            <acme:print value="${maxCostProject}"/>
        </td>
    </tr>
       
  </table>

<acme:return/>