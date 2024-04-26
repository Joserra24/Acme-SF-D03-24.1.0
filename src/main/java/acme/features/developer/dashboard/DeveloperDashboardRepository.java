
package acme.features.developer.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.TrainingModule;
import acme.entities.TrainingSession;
import acme.roles.Developer;

@Repository
public interface DeveloperDashboardRepository extends AbstractRepository {

	@Query("select d from Developer d where d.userAccount.id = :id")
	Developer findOneDeveloperByUserAccountId(int id);

	@Query("select count(tm) from TrainingModule tm where tm.creationMoment < tm.updateMoment and tm.developer.id = :id")
	int totalTrainingModulesWithUpdateMoment(int id);

	@Query("select count(ts) from TrainingSession ts where ts.link is not null and ts.trainingModule.developer.id = :id")
	int totalTrainingSessionsWithLink(int id);

	@Query("select avg(tm.estimatedTotalTime) from TrainingModule tm where tm.developer.id = :id")
	Double avgTimeTrainingModule(int id);

	@Query("select stddev(tm.estimatedTotalTime) from TrainingModule tm where tm.developer.id = :id")
	Double devTimeTrainingModule(int id);

	@Query("select min(tm.estimatedTotalTime) from TrainingModule tm where tm.developer.id = :id")
	Double minTimeTrainingModule(int id);

	@Query("select max(tm.estimatedTotalTime) from TrainingModule tm where tm.developer.id = :id")
	Double maxTimeTrainingModule(int id);

	@Query("SELECT tm FROM TrainingModule tm WHERE tm.developer.id = :developerId")
	List<TrainingModule> findAllTrainingModulesByDeveloperId(int developerId);

	@Query("SELECT ts FROM TrainingSession ts WHERE ts.trainingModule.id = :moduleId")
	List<TrainingSession> findManyTrainingSessionsByTrainingModuleId(int moduleId);

}
