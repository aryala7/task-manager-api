package la.arya.taskmanager;

import la.arya.taskmanager.model.Task;
import la.arya.taskmanager.repository.TaskRepository;
import la.arya.taskmanager.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @Test
    public void shouldGetAllTasks() {
        List<Task> tasks = List.of(
                new Task(1L,"Task1","Description 1", Task.Status.OPEN),
                new Task(2L,"Task2","Description 2", Task.Status.DONE)
        );
        when(taskRepository.findAll()).thenReturn(tasks);
        List<Task> allTasks = taskService.getAllTasks();

        assertEquals(2,allTasks.size());
        verify(taskRepository,times(1)).findAll();
    }

    @Test
    public void shouldCreateTask() {

        Task newTask = new Task(null, "New Task", "New Description", Task.Status.OPEN);
        Task savedTask = new Task(1L, "New Task", "New Description", Task.Status.OPEN);
        when(taskRepository.save(newTask)).thenReturn(savedTask);

        Task result = taskService.createTask(newTask);

        assertNotNull(result.getId());
        assertEquals("New Task", result.getTitle());
        verify(taskRepository, times(1)).save(newTask);

    }

    @Test
    public void shouldUpdateTask() {

        long taskId = 1L;
        Task existingTask = new Task(taskId, "Old Task", "Old Description", Task.Status.OPEN);
        Task updatedTask = new Task(taskId, "Updated Task", "Updated Description", Task.Status.DONE);
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(existingTask)).thenReturn(updatedTask);

        Task result = taskService.updateTask(taskId, updatedTask);
        assertEquals("Updated Task", result.getTitle());
        assertEquals(Task.Status.DONE, result.getStatus());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(existingTask);
    }

}
