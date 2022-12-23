package full.projmanager.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Getter
@Setter
@Document(collection = "projects")
public class Project {
    @Id
    private String id;

    private String name;
    private String owner;
}
