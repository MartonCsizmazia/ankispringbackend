package com.example.firstreactspring.controller;

import com.example.firstreactspring.model.DataModel;
import com.example.firstreactspring.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/search")
    public ResponseEntity<List<DataModel>> searchData(@RequestParam(required = false) String value) {
        try {
            List<DataModel> searchResults;

            if (value != null && !value.isEmpty()) {
                // Perform specific search when the value is not empty
                searchResults = dataService.searchData(value);
            } else {
                // Return all data when the value is empty
                searchResults = dataService.getAllData();
            }

            return new ResponseEntity<>(searchResults, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}