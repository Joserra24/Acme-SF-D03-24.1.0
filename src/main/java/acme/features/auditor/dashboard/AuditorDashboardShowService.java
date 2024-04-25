
package acme.features.auditor.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.enumerated.Type;
import acme.forms.AuditorDashboards;
import acme.roles.Auditor;

@Service
public class AuditorDashboardShowService extends AbstractService<Auditor, AuditorDashboards> {

	@Autowired
	private AuditorDashboardRepository repository;


	@Override
	public void authorise() {
		boolean status;
		status = super.getRequest().getPrincipal().hasRole(Auditor.class);

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Auditor auditor;
		int id;

		id = super.getRequest().getPrincipal().getActiveRoleId();
		auditor = this.repository.findOneAuditorByUserAccountId(id);

		AuditorDashboards dashboard;

		int totalCodeAuditsStatic;
		int totalCodeAuditsDynamic;

		Double avgAuditRecords;
		Double devAuditRecords;
		Double minAuditRecords;
		Double maxAuditRecords;

		Double avgPeriod;
		Double devPeriod;
		Double minPeriod;
		Double maxPeriod;

		totalCodeAuditsStatic = this.repository.totalCodeAuditsType(Type.STATIC, id);
		totalCodeAuditsDynamic = this.repository.totalCodeAuditsType(Type.DYNAMIC, id);

		/*
		 * totalMustUserStory = this.repository.totalUserStoriesWithPriority(Priority.MUST, id);
		 * totalShouldUserStory = this.repository.totalUserStoriesWithPriority(Priority.SHOULD, id);
		 * totalCouldUserStory = this.repository.totalUserStoriesWithPriority(Priority.COULD, id);
		 * totalWontUserStory = this.repository.totalUserStoriesWithPriority(Priority.WONT, id);
		 * 
		 * avgEstimatedCostUserStory = this.repository.avgEstimatedCostUserStory(id);
		 * devEstimatedCostUserStory = this.repository.devEstimatedCostUserStory(id);
		 * minEstimatedCostUserStory = this.repository.minEstimatedCostUserStory(id);
		 * maxEstimatedCostUserStory = this.repository.maxEstimatedCostUserStory(id);
		 * 
		 * avgCostProject = this.repository.avgCostProject(id);
		 * devCostProject = this.repository.devCostProject(id);
		 * minCostProject = this.repository.minCostProject(id);
		 * maxCostProject = this.repository.maxCostProject(id);
		 */

		dashboard = new AuditorDashboards();
		dashboard.setTotalCodeAuditsStatic(totalCodeAuditsStatic);
		dashboard.setTotalCodeAuditsDynamic(totalCodeAuditsDynamic);
		/*
		 * dashboard.setTotalShouldUserStory(totalShouldUserStory);
		 * dashboard.setTotalCouldUserStory(totalCouldUserStory);
		 * dashboard.setTotalWontUserStory(totalWontUserStory);
		 * dashboard.setAvgEstimatedCostUserStory(avgEstimatedCostUserStory);
		 * dashboard.setDevEstimatedCostUserStory(devEstimatedCostUserStory);
		 * dashboard.setMinEstimatedCostUserStory(minEstimatedCostUserStory);
		 * dashboard.setMaxEstimatedCostUserStory(maxEstimatedCostUserStory);
		 * dashboard.setAvgCostProject(avgCostProject);
		 * dashboard.setDevCostProject(devCostProject);
		 * dashboard.setMinCostProject(minCostProject);
		 * dashboard.setMaxCostProject(maxCostProject);
		 */

		super.getBuffer().addData(dashboard);
	}

	@Override
	public void unbind(final AuditorDashboards object) {
		Dataset dataset;

		dataset = super.unbind(object, "totalCodeAuditsStatic", "totalCodeAuditsDynamic");
		/*
		 * dataset = super.unbind(object, //
		 * "totalMustUserStory", "totalShouldUserStory", //
		 * "totalCouldUserStory", "totalWontUserStory", //
		 * "avgEstimatedCostUserStory", "devEstimatedCostUserStory", //
		 * "minEstimatedCostUserStory", "maxEstimatedCostUserStory", //
		 * "avgCostProject", "devCostProject", //
		 * "minCostProject", "maxCostProject");
		 */

		super.getResponse().addData(dataset);
	}

}
