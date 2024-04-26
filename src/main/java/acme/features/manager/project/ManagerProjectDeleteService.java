
package acme.features.manager.project;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.Project;
import acme.entities.ProjectUserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectDeleteService extends AbstractService<Manager, Project> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private ManagerProjectRepository repository;

	// AbstractService interface ----------------------------------------------


	@Override
	public void authorise() {
		final boolean status;
		int projectId;
		Project object;
		Manager manager;

		projectId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProjectById(projectId);
		manager = object == null ? null : object.getManager();
		status = object != null && object.isDraftMode() && super.getRequest().getPrincipal().hasRole(manager);
		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Project object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneProjectById(id);
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final Project object) {
		assert object != null;

		super.bind(object, "code", "title", "abstractProject", "cost", "link", "draftMode");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		Collection<ProjectUserStory> projectUserStories;

		projectUserStories = this.repository.findManyProjectUserStoriesByProjectId(object.getId());
		this.repository.deleteAll(projectUserStories);
		this.repository.delete(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "code", "title", "abstractProject", "cost", "link", "draftMode");
		super.getResponse().addData(dataset);
	}
}
