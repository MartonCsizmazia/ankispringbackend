package com.example.firstreactspring.controller;

import com.example.firstreactspring.model.DataModel;
import com.example.firstreactspring.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    DataService dataService;

    @PostMapping("/save")
    public void saveData(@RequestBody DataModel newData) {
            // Save the new DataModel to the database
        dataService.saveData(newData);
    }

    @GetMapping("/getAll")
    public List<DataModel> getAllData() {
        // Return all the stored data from the database
        return dataService.getAllData();
    }

    @PutMapping("/update/{id}")
    public void updateData(@PathVariable Long id, @RequestBody DataModel newData) {
        // Save the updated DataModel to the database
        dataService.updateData(id, newData);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteData(@PathVariable Long id) {
        // Delete the DataModel from the database
        dataService.deleteData(id);
    }
}