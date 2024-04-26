
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.Project;
import acme.entities.ProjectUserStory;
import acme.entities.UserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryListMineService extends AbstractService<Manager, ProjectUserStory> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectUserStoryRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		final boolean status;
		status = super.getRequest().getPrincipal().hasRole(Manager.class);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		int managerId;
		managerId = super.getRequest().getPrincipal().getActiveRoleId();

		Collection<ProjectUserStory> objects;

		objects = this.repository.findManyProjectsUserStoriesByManagerId(managerId);

		super.getBuffer().addData(objects);
	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Project project;
		UserStory userStory;
		int projectUserStoryId;
		Dataset dataset;

		projectUserStoryId = object.getId();
		project = this.repository.findOneProjectByProjectUserStoryId(projectUserStoryId);
		userStory = this.repository.findOneUserStoryByProjectUserStoryId(projectUserStoryId);

		dataset = super.unbind(object, "project", "userStory");
		dataset.put("project", project);
		dataset.put("userStory", userStory);

		super.getResponse().addData(dataset);
	}

}
