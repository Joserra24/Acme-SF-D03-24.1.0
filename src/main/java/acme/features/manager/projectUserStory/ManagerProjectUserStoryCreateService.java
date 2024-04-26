
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.Project;
import acme.entities.ProjectUserStory;
import acme.entities.UserStory;
import acme.roles.Manager;

@Service
public class ManagerProjectUserStoryCreateService extends AbstractService<Manager, ProjectUserStory> {

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
		ProjectUserStory object = new ProjectUserStory();
		super.getBuffer().addData(object);
	}

	@Override
	public void bind(final ProjectUserStory object) {
		assert object != null;
		super.bind(object, "project", "userStory");

	}

	@Override
	public void perform(final ProjectUserStory object) {
		assert object != null;

		this.repository.save(object);
	}

	@Override
	public void validate(final ProjectUserStory object) {
		assert object != null;

		if (!super.getBuffer().getErrors().hasErrors("userStory")) {
			ProjectUserStory existing;

			existing = this.repository.findOneProjectUserStoryByProjectIdAndUserStoryId(object.getProject().getId(), object.getUserStory().getId());
			super.state(existing == null, "userStory", "manager.project-user-story.form.error.duplicated");

			super.state(object.getProject().isDraftMode() || !object.getUserStory().isDraftMode(), "userStory", "manager.project-user-story.form.error.published-project");
		}

		if (!super.getBuffer().getErrors().hasErrors("project")) {
			ProjectUserStory existing;

			existing = this.repository.findOneProjectUserStoryByProjectIdAndUserStoryId(object.getProject().getId(), object.getUserStory().getId());
			super.state(existing == null, "project", "manager.project-user-story.form.error.duplicated");

			super.state(object.getProject().isDraftMode() || !object.getUserStory().isDraftMode(), "project", "manager.project-user-story.form.error.published-project");
		}

	}

	@Override
	public void unbind(final ProjectUserStory object) {
		assert object != null;

		Collection<UserStory> userStories;
		Collection<Project> projects;
		SelectChoices choicesUserStories;
		SelectChoices choicesProjects;
		Dataset dataset;
		int managerId;

		managerId = super.getRequest().getPrincipal().getActiveRoleId();

		userStories = this.repository.findManyUserStoriesByManagerId(managerId);
		choicesUserStories = SelectChoices.from(userStories, "title", object.getUserStory());

		projects = this.repository.findManyProjectsByManagerId(managerId);
		choicesProjects = SelectChoices.from(projects, "code", object.getProject());

		dataset = super.unbind(object, "userStory", "project");
		dataset.put("userStory", choicesUserStories.getSelected().getKey());
		dataset.put("userStories", choicesUserStories);
		dataset.put("project", choicesProjects.getSelected().getKey());
		dataset.put("projects", choicesProjects);

		super.getResponse().addData(dataset);
	}
}
