
package acme.features.developer.trainingModule;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.Project;
import acme.entities.TrainingModule;
import acme.entities.TrainingSession;
import acme.roles.Developer;

@Repository
public interface DeveloperTrainingModuleRepository extends AbstractRepository {

	//List
	@Query("select tm from TrainingModule tm where tm.developer.id = :developerId")
	Collection<TrainingModule> findManyTrainingModulesByDeveloperId(int developerId);

	@Query("select ts from TrainingSession ts where ts.trainingModule.id = :trainingModuleId")
	Collection<TrainingSession> findManyTrainingSessionsByTrainingModuleId(int trainingModuleId);

	default int findEstimatedTotalTimeOfTrainingModule(final int id) {
		int totalTime = 0;
		final Collection<TrainingSession> trainingSessions = this.findManyTrainingSessionsByTrainingModuleId(id);
		for (final TrainingSession session : trainingSessions) {
			final long diff = session.getEndPeriod().getTime() - session.getStartPeriod().getTime();
			final long hours = diff / (1000 * 60 * 60);
			totalTime += Math.abs(hours);
		}
		return totalTime;
	}

	//Show
	@Query("select tm from TrainingModule tm where tm.id = :trainingModuleId")
	TrainingModule findOneTrainingModuleById(int trainingModuleId);

	@Query("select p from Project p where p.draftMode = true")
	Collection<Project> findManyProjectsAvailable();

	//Create
	@Query("select d from Developer d where d.id = :id")
	Developer findOneDeveloperById(int id);

	@Query("select t from TrainingModule t where t.code = :code")
	TrainingModule findOneTrainingModuleByCode(String code);

	@Query("select p from Project p where p.id = :projectId")
	Project findOneProjectById(int projectId);

	@Query("select p from Project p")
	Collection<Project> findAllProjects();

}
