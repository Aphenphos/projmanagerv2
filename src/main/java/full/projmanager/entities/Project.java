package full.projmanager.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Getter
@Setter
@Document(collection = "projects")
public class Project {
    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String name;
    private String description;
    private List<String> notes;
    private String owner;
}
