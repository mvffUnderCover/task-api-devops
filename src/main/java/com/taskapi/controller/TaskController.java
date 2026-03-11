package com.taskapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.task.dto.StatusUpdateRequest;
import com.taskapi.model.Task;
import com.taskapi.services.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "http://localhost:3000"
	})

@Tag(name = "Task API", description = "Gestion des tâches")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les tâches")
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.saveTask(task);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une tâche par son id")
    public Task getTask(@PathVariable Long id) {
        return service.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une tâche")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }

    @PutMapping("/{id}/cancel")
    @Operation(summary = "Annuler une tâche")
    public Task cancelTask(@PathVariable Long id){
        return service.cancelTask(id);
    }
    @PutMapping("/{id}/status")
    public Task updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest request) {

        return service.updateStatus(id, request.getStatus());
    }
}