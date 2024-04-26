<%--
- menu.jsp
-
- Copyright (C) 2012-2024 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.josbanbot-link" action="https://www.realbetisbalompie.es/club/estadio-benito-villamarin/"/>
			<acme:menu-suboption code="master.menu.anonymous.sonrusmor-link" action="https://www.nationalgeographic.es/animales/2021/09/los-pandas-salen-de-la-lista-de-especies-en-peligro-en-china-pero-la-amenaza-continua"/>
			<acme:menu-suboption code="master.menu.anonymous.manpalpin-link" action="https://youtube.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.javramnun-link" action="https://www.twitch.tv/illojuan"/>
			<acme:menu-suboption code="master.menu.anonymous.rubpergar-link" action="https://www.hltv.org"/>
			<acme:menu-separator/>			
			<acme:menu-suboption code="master.menu.anonymous.claim-list" action="/any/claim/list"/>
			<acme:menu-suboption code="master.menu.anonymous.training-module-list" action="/any/training-module/list"/>		
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-initial" action="/administrator/system/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-sample" action="/administrator/system/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.list.banner" action="/administrator/banner/list" access="isAuthenticated()"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-down" action="/administrator/system/shut-down"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.favourite-link" action="http://www.example.com/"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.developer" access="hasRole('Developer')">
			<acme:menu-suboption code="master.menu.developer.training-modules" action="/developer/training-module/list"/>
			<acme:menu-suboption code="master.menu.developer.training-sessions" action="/developer/training-session/list-all"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.developer.show-developer-dashboard" action="/developer/developer-dashboard/show"/>
		</acme:menu-option>
		<acme:menu-option code="master.menu.manager" access="hasRole('Manager')">
			<acme:menu-suboption code="master.menu.manager.projects" action="/manager/project/list"/>
			<acme:menu-suboption code="master.menu.manager.user-stories" action="/manager/user-story/list-all"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.manager.show-manager-dashboard" action="/manager/manager-dashboard/show"/>
		</acme:menu-option>
	</acme:menu-left>
	
	<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
		<acme:menu-suboption code="master.menu.auditor.code-audits" action="/auditor/code-audit/list"/>
		<acme:menu-separator/>
		<acme:menu-suboption code="master.menu.auditor.show-auditor-dashboard" action="/auditor/auditor-dashboards/show"/>
	</acme:menu-option>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/anonymous/system/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.become-developer" action="/authenticated/developer/create" access="!hasRole('Developer')"/>
			<acme:menu-suboption code="master.menu.user-account.developer" action="/authenticated/developer/update" access="hasRole('Developer')"/>
				<acme:menu-suboption code="master.menu.user-account.become-manager" action="/authenticated/manager/create" access="!hasRole('Manager')"/>
			<acme:menu-suboption code="master.menu.user-account.manager" action="/authenticated/manager/update" access="hasRole('Manager')"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/authenticated/system/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

