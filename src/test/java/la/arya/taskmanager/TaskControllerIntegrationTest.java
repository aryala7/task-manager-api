package la.arya.taskmanager;

import la.arya.taskmanager.repository.TaskRepository;
import la.arya.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  // For HTTP methods
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void shouldReturnAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        mockMvc.perform(post("/tasks/add")
                        .queryParam("title", "Test Title")
                        .queryParam("description", "Test Description")
                        .queryParam("status", "OPEN")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"))
                .andExpect(jsonPath("$.status").value("OPEN"));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        Long taskId = 1L;

        String updatedTasks = """
                {
                    "title": "Updated Title",
                    "description": "Updated Description",
                    "status": "OPEN"
                }
                
                """;

        mockMvc.perform(
                put("/tasks/{id}",taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedTasks))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    public void ShouldDeleteTask() throws Exception {
        long taskId = 1L;
        mockMvc.perform(
                delete("/tasks/{id}",taskId))
                .andExpect(status().isNoContent());
    }
}
