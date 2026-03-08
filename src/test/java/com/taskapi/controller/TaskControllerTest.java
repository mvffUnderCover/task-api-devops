package com.taskapi.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskapi.model.Task;
import com.taskapi.model.TaskStatus;
import com.taskapi.services.TaskService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    @Autowired
    private ObjectMapper objectMapper;

    // -------- GET ALL TASKS --------

    @Test
    void shouldReturnAllTasks() throws Exception {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test task");

        when(service.getAllTasks()).thenReturn(List.of(task));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk());
    }

    // -------- GET TASK BY ID --------

    @Test
    void shouldReturnTaskById() throws Exception {

        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task 1");

        when(service.getTaskById(1L)).thenReturn(task);

        mockMvc.perform(get("/api/tasks/1"))
                .andExpect(status().isOk());
    }

    // -------- CREATE TASK --------

    @Test
    void shouldCreateTask() throws Exception {

        Task task = new Task();
        task.setTitle("New Task");
        task.setDescription("Test description");
        task.setStartDate(LocalDateTime.now());
        task.setStatus(TaskStatus.TODO);

        when(service.saveTask(org.mockito.ArgumentMatchers.any(Task.class)))
                .thenReturn(task);

        mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk());
    }

    // -------- DELETE TASK --------

    @Test
    void shouldDeleteTask() throws Exception {

        doNothing().when(service).deleteTask(1L);

        mockMvc.perform(delete("/api/tasks/1"))
                .andExpect(status().isOk());
    }

    // -------- CANCEL TASK --------

    @Test
    void shouldCancelTask() throws Exception {

        Task task = new Task();
        task.setId(1L);
        task.setStatus(TaskStatus.CANCELLED);

        when(service.cancelTask(1L)).thenReturn(task);

        mockMvc.perform(put("/api/tasks/1/cancel"))
                .andExpect(status().isOk());
    }
}