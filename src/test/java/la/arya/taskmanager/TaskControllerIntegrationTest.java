package la.arya.taskmanager;

import la.arya.taskmanager.model.Task;
import la.arya.taskmanager.repository.TaskRepository;
import la.arya.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  // For HTTP methods
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TaskRepository taskRepository;

    @Mock
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

        Task existingTask = new Task(taskId, "Old Task", "Old Description", Task.Status.OPEN);
        Task updatedTask = new Task(taskId, "Updated Task", "Updated Description", Task.Status.DONE);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask)); // Mock findById
        when(taskRepository.save(updatedTask)).thenReturn(updatedTask); // Mock save

        Task result = taskService.updateTask(taskId, updatedTask);

        assertEquals("Updated Description", result.getDescription());
        assertEquals("Updated Task", result.getTitle());
        assertEquals(Task.Status.DONE, result.getStatus());

        verify(taskRepository, times(1)).findById(taskId); // Ensure findById was called once
        verify(taskRepository, times(1)).save(updatedTask); // Ensure save was called once
    }
}
