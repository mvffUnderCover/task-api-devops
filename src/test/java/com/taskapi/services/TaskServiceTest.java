package com.taskapi.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.taskapi.model.Task;
import com.taskapi.model.TaskStatus;
import com.taskapi.repository.TaskRepository;

public class TaskServiceTest {

    private TaskRepository repository;
    private TaskService service;

    @BeforeEach
    void setup() {
        repository = mock(TaskRepository.class);
        service = new TaskService(repository);
    }

    @Test
    void shouldReturnTaskById() {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test task");

        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Task result = service.getTaskById(1L);

        assertEquals("Test task", result.getTitle());
    }

    @Test
    void shouldSetDefaultStatusWhenSavingTask() {

        Task task = new Task();
        task.setTitle("New task");

        when(repository.save(any(Task.class))).thenAnswer(i -> i.getArgument(0));

        Task saved = service.saveTask(task);

        assertEquals(TaskStatus.TODO, saved.getStatus());
        assertNotNull(saved.getStartDate());
    }

    @Test
    void shouldCancelTask() {

        Task task = new Task();
        task.setId(1L);
        task.setStatus(TaskStatus.TODO);

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(task)).thenReturn(task);

        Task cancelled = service.cancelTask(1L);

        assertEquals(TaskStatus.CANCELLED, cancelled.getStatus());
    }
}