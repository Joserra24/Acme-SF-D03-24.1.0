
package acme.features.manager.projectUserStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.Project;
import acme.entities.ProjectUserStory;
import acme.entities.UserStory;

@Repository
public interface ManagerProjectUserStoryRepository extends AbstractRepository {

	@Query("select pus from ProjectUserStory pus where pus.project.manager.id = :managerId")
	Collection<ProjectUserStory> findManyProjectsUserStoriesByManagerId(int managerId);

	@Query("SELECT pus.project FROM ProjectUserStory pus WHERE pus.id = :id")
	Project findOneProjectByProjectUserStoryId(int id);

	@Query("SELECT pus.userStory FROM ProjectUserStory pus WHERE pus.id = :id")
	UserStory findOneUserStoryByProjectUserStoryId(int id);

	@Query("SELECT pus FROM ProjectUserStory pus WHERE pus.project.id = :projectId AND pus.userStory.id = :userStoryId")
	ProjectUserStory findOneProjectUserStoryByProjectIdAndUserStoryId(int projectId, int userStoryId);

	@Query("SELECT us FROM UserStory us WHERE us.manager.id = :id")
	Collection<UserStory> findManyUserStoriesByManagerId(int id);

	@Query("SELECT p FROM Project p WHERE p.manager.id = :id")
	Collection<Project> findManyProjectsByManagerId(int id);

	@Query("SELECT pus FROM ProjectUserStory pus WHERE pus.id = :id")
	ProjectUserStory findOneProjectUserStoryById(int id);
}
