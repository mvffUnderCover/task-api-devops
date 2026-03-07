package com.taskapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.taskapi.model.Task;
import com.taskapi.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task saveTask(Task task){
        return repository.save(task);
    }

    public Optional<Task> getTaskById(Long id){
        return repository.findById(id);
    }

    public void deleteTask(Long id){
        repository.deleteById(id);
    }

    public Task cancelTask(Long id){
        Task task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus("CANCELLED");

        return repository.save(task);
    }
}