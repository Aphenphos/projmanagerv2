package full.projmanager.services;

import com.mongodb.MongoException;
import full.projmanager.entities.Project;
import full.projmanager.repositories.ProjectRepository;
import full.projmanager.types.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void saveProject(ProjectRequest newProjectRequest, String username) {
    Project toSave = new Project();
    toSave.setName(newProjectRequest.getName());
    toSave.setOwner(username);
    projectRepository.save(toSave);
    }

    public void removeProject(String owner, String mongoId) throws AuthenticationException {
        Optional<Project> projInfo = projectRepository.findById(mongoId);
        if (projInfo.isPresent()) {
            if (Objects.equals(projInfo.get().getOwner(), owner)) {
                projectRepository.delete(projInfo.get());
            } else {
                throw new AuthenticationException("Could not Authenticate");
            }
        } else {
            throw new MongoException("Could not find project");
        }

    }

}
