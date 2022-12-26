package full.projmanager.services;

import full.projmanager.entities.Project;
import full.projmanager.repositories.ProjectRepository;
import full.projmanager.types.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
