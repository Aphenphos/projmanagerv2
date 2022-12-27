package full.projmanager.services;

import com.mongodb.MongoException;
import full.projmanager.entities.Project;
import full.projmanager.repositories.ProjectRepository;
import full.projmanager.types.NewProjectRequest;
import full.projmanager.types.UpdateProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public void saveProject(NewProjectRequest newProjectRequest, String username) {
    Project toSave = new Project();
    toSave.setName(newProjectRequest.getName());
    toSave.setOwner(username);
    toSave.setDescription(newProjectRequest.getDescription());
    toSave.setNotes(newProjectRequest.getNotes());
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

    public void updateProject(String owner, String mongoId, UpdateProjectRequest changes) throws AuthenticationException {
        Optional<Project> projInfo = projectRepository.findById(mongoId);
        if(projInfo.isPresent()) {
            if (Objects.equals(projInfo.get().getOwner(), owner)) {
                //can potientially optimize by creating an "initial" object with "old" values and update only where they changed.
                Project curProject = projInfo.get();
                curProject.setName(changes.getName());
                curProject.setDescription(changes.getDescription());
                curProject.setNotes(changes.getNotes());
                projectRepository.save(curProject);
            } else {
                throw new AuthenticationException("Could not Authenticate");
            }
        } else {
            throw new MongoException("Could not find project");
        }
    }

}
