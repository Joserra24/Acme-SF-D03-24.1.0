
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.client.data.models.Dataset;
import acme.client.services.AbstractService;
import acme.client.views.SelectChoices;
import acme.entities.Project;
import acme.entities.TrainingModule;
import acme.enumerated.DifficultyLevel;
import acme.roles.Developer;

@Service
public class DeveloperTrainingModuleShowService extends AbstractService<Developer, TrainingModule> {

	// Internal state ---------------------------------------------------------
	@Autowired
	private DeveloperTrainingModuleRepository repository;


	// AbstractService interface ----------------------------------------------
	@Override
	public void authorise() {
		boolean status;
		int trainingModuleId;
		TrainingModule object;
		Developer developer;

		trainingModuleId = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingModuleById(trainingModuleId);
		developer = object == null ? null : object.getDeveloper();
		status = super.getRequest().getPrincipal().hasRole(developer) || object != null && !object.isDraftMode();

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		TrainingModule object;
		int id;

		id = super.getRequest().getData("id", int.class);
		object = this.repository.findOneTrainingModuleById(id);
		super.getBuffer().addData(object);
	}

	@Override
	public void unbind(final TrainingModule object) {
		assert object != null;

		Dataset dataset;
		Collection<Project> projects;
		SelectChoices choices;
		SelectChoices choicesLevel;

		projects = this.repository.findAllProjects();

		choicesLevel = SelectChoices.from(DifficultyLevel.class, object.getDifficultyLevel());
		choices = SelectChoices.from(projects, "title", object.getProject());

		dataset = super.unbind(object, "code", "creationMoment", "details", "difficultyLevel", "estimatedTotalTime", "updateMoment", "link", "project", "draftMode");
		dataset.put("difficultyLevel", choicesLevel);
		dataset.put("project", choices.getSelected().getKey());
		dataset.put("projects", choices);

		final int estimatedTotalTime = this.repository.findEstimatedTotalTimeOfTrainingModule(object.getId());
		dataset.put("estimatedTotalTime", estimatedTotalTime);

		super.getResponse().addData(dataset);
	}
}
