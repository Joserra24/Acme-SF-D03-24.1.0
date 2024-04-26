
package acme.features.manager.userStory;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.Project;
import acme.entities.ProjectUserStory;
import acme.entities.UserStory;
import acme.roles.Manager;

@Repository
public interface ManagerUserStoryRepository extends AbstractRepository {

	@Query("select us from UserStory us where us.manager.id = :managerId")
	Collection<UserStory> findManyUserStoryByManagerId(int managerId);

	@Query("select us from UserStory us where us.id = :userStoryId")
	UserStory findOneUserStoryById(int userStoryId);

	@Query("select pus from ProjectUserStory pus where pus.userStory.id = :userStoryId")
	Collection<ProjectUserStory> findManyProjectUserStoriesByUserStoryId(int userStoryId);

	@Query("select m from Manager m where m.id = :id")
	Manager findOneManagerById(int id);

	@Query("select p from Project p where p.id = :id")
	Project findOneProjectById(int id);

	@Query("select pus from ProjectUserStory pus where pus.project.id = :masterId")
	Collection<ProjectUserStory> findManyProjectUserStoriesByProjectId(int masterId);

}
