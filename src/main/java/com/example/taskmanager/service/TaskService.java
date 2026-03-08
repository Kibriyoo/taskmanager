package com.example.taskmanager.service;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.model.Task;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task saveTask(Task task) {
        return repository.save(task);
    }

    public void deleteTask(Long id) {
        repository.deleteById(id);
    }

    // Fixed: Moved outside of deleteTask and updated variable name to 'repository'
    public Task getTaskById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
    }

    // Fixed: Updated variable name to 'repository'
    public void updateTask(Task task) {
        repository.save(task);
    }
}