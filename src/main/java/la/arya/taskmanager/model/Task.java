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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="title is required")
    private String title;

    private String description;


    private Status status = Status.OPEN;


    public enum Status {
        OPEN, IN_PROGRESS, DONE
    }
}
