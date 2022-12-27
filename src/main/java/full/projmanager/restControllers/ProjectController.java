package full.projmanager.restControllers;

import com.mongodb.MongoException;
import full.projmanager.entities.Project;
import full.projmanager.repositories.ProjectRepository;
import full.projmanager.services.ProjectService;
import full.projmanager.types.NewProjectRequest;
import full.projmanager.types.UpdateProjectRequest;
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
    public NewProjectRequest newProject(@RequestBody NewProjectRequest newProjectRequest) throws MongoException{
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        projectService.saveProject(newProjectRequest, uName);
            return newProjectRequest;
    }

    @DeleteMapping("/rmProject")
    public void rmProject(@RequestParam(value = "id") String id) throws Exception {
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (uName != null ) {
            projectService.removeProject(uName, id);
        } else {
            throw new Exception("Failed to authenticate.");
        }
    }

    @PutMapping("/updateProject")
    public void upProject(@RequestParam(value = "id") String id, @RequestBody UpdateProjectRequest changes) throws Exception {
        var uName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (uName != null) {
            projectService.updateProject(uName, id, changes);
        } else {
            throw new Exception("Failed to authenticate");
        }
    }
}
