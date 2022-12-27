package full.projmanager.types;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProjectRequest {
    private String name;
    private String description;
    private List<String> notes;

}
