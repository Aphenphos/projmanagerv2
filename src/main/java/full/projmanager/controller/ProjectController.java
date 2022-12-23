package full.projmanager.controller;

import com.mongodb.MongoException;
import full.projmanager.entities.Project;
import full.projmanager.repositories.ProjectRepository;
import full.projmanager.types.ProjectRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {
    ProjectRepository projectRepository;

    @GetMapping("/getProjects")
    public List<Project> getProjects() throws AuthenticationException {
        var u = SecurityContextHolder.getContext().getAuthentication().getName();
        if (u != null) {
            return new ArrayList<Project>(projectRepository.findByOwner(u));
        }
        throw new AuthenticationException("Failed to get Username");
    }

    @PostMapping("/newProject")
    public Project newProject(@RequestBody ProjectRequest projectRequest) throws MongoException{
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        Project toSave = new Project();
        toSave.setOwner(uName);
        toSave.setName(projectRequest.getName());

            return projectRepository.save(toSave);
    }
}
