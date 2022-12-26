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
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (uName != null) {
            return new ArrayList<Project>(projectRepository.findByOwner(uName));
        }
        throw new AuthenticationException("Failed to get Username");
    }

    @PostMapping("/newProject")
    public ProjectRequest newProject(@RequestBody ProjectRequest projectRequest) throws MongoException{
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        projectService.saveProject(projectRequest, uName);
            return projectRequest;
    }

    @DeleteMapping("/rmProject")
    public void rmProject(@RequestParam(value = "id") String id) throws Exception {
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(id);
        System.out.println(uName);
        if (uName != null ) {
            projectService.removeProject(uName, id);
        } else {
            throw new Exception("Failed to authenticate.");
        }
    }
}
