package com.example.demo.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TaskService {

    @Autowired
    TaskRepository repository;

    public List<Task> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var task = repository.findById(id);
        if(task.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public void save(@Valid Task task) {
        repository.save(task);
    }
    
}