
package acme.features.manager.project;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.entities.Project;
import acme.entities.ProjectUserStory;
import acme.entities.UserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectPublishService extends AbstractService<Manager, Project> {

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

		super.bind(object, "code", "title", "abstractProject", "cost", "link");
	}

	@Override
	public void validate(final Project object) {
		assert object != null;

		boolean draftModeProjectUserStories;

		final Collection<ProjectUserStory> projectUserStories = this.repository.findManyProjectUserStoriesByProjectId(object.getId());
		Collection<UserStory> userStories = projectUserStories.stream().map(ProjectUserStory::getUserStory).collect(Collectors.toList());
		super.state(!userStories.isEmpty(), "*", "manager.project.form.error.projectWithOutUserStories");
		if (!userStories.isEmpty()) {
			draftModeProjectUserStories = userStories.stream().anyMatch(userStory -> !userStory.isDraftMode());
			super.state(draftModeProjectUserStories, "*", "manager.project.form.error.userStoriesInDraftMode");
		}
	}

	@Override
	public void perform(final Project object) {
		assert object != null;

		object.setDraftMode(false);
		this.repository.save(object);
	}

	@Override
	public void unbind(final Project object) {
		assert object != null;
		Dataset dataset;
		dataset = super.unbind(object, "code", "title", "abstractProject", "cost", "link", "draftMode");
		super.getResponse().addData(dataset);
	}
}
