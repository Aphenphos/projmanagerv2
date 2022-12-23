package full.projmanager.repositories;

import full.projmanager.entities.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {
    @Query("{owner:'?0'}")
    List<Project> findByOwner(String ownerUsername);
}
