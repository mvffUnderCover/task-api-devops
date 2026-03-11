package com.taskapi.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taskapi.model.Task;
import com.taskapi.model.TaskStatus;
import com.taskapi.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task saveTask(Task task) {

        // règle métier
        if (task.getStartDate() == null) {
            task.setStartDate(LocalDateTime.now());
        }

        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }

        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    public Task cancelTask(Long id) {

        Task task = getTaskById(id);

        task.setStatus(TaskStatus.CANCELLED);

        return repository.save(task);
    }

    public Task updateStatus(Long id, TaskStatus status) {

        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);

        return repository.save(task);
    }
}