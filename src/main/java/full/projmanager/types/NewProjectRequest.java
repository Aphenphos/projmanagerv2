package full.projmanager.types;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewProjectRequest {
    String name;
    String description;
    List<String> notes;
}
