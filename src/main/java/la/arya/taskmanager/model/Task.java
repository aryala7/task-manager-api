package la.arya.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="TASK")
public class Task {

    public Task() {

    }
    public Task(Long id,String title,String Description,Status status) {
        this.id = id;
        this.title = title;
        this.description = Description;
        this.status = status;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="title is required")
    private String title;

    private String description;


    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.OPEN;


    public enum Status {
        OPEN, IN_PROGRESS, DONE
    }
}
