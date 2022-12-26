package full.projmanager.controller;

import com.mongodb.MongoException;
import full.projmanager.entities.Project;
import full.projmanager.repositories.ProjectRepository;
import full.projmanager.services.ProjectService;
import full.projmanager.types.ProjectRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProjectController {
    private ProjectService projectService;
    private ProjectRepository projectRepository;

    @GetMapping("/getProjects")
    public List<Project> getProjects() throws AuthenticationException {
        var u = SecurityContextHolder.getContext().getAuthentication().getName();
        if (u != null) {
            return new ArrayList<Project>(projectRepository.findByOwner(u));
        }
        throw new AuthenticationException("Failed to get Username");
    }

    @PostMapping("/newProject")
    public ProjectRequest newProject(@RequestBody ProjectRequest projectRequest) throws MongoException{
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        projectService.saveProject(projectRequest, uName);
            return projectRequest;
    }
}
