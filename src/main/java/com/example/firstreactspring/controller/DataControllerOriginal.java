package com.example.firstreactspring.controller;

import com.example.firstreactspring.model.DataModel;
import com.example.firstreactspring.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// DataController.java
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/data/safe")
public class DataControllerOriginal {

    @Autowired
    DataRepository dataRepository;

    @PostMapping("/save")
    public ResponseEntity<String> saveData(@RequestBody String newData) {
        try {
            // Create a new DataModel instance
            DataModel newDataModel = new DataModel();
            newDataModel.setData(newData);

            // Save the new DataModel to the database
            dataRepository.save(newDataModel);

            return ResponseEntity.ok("Data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving data");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<DataModel>> getAllData() {
        // Return all the stored data from the database
        List<DataModel> allData = dataRepository.findAll();
        return ResponseEntity.ok(allData);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateData(@PathVariable Long id, @RequestBody String newData) {
        try {
            Optional<DataModel> dataOptional = dataRepository.findById(id);

            if (dataOptional.isPresent()) {
                DataModel data = dataOptional.get();
                data.setData(newData);

                // Save the updated DataModel to the database
                dataRepository.save(data);

                return ResponseEntity.ok("Data updated successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteData(@PathVariable Long id) {
        try {
            Optional<DataModel> dataOptional = dataRepository.findById(id);

            if (dataOptional.isPresent()) {
                // Delete the DataModel from the database
                dataRepository.deleteById(id);

                return ResponseEntity.ok("Data deleted successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid ID format");
        }
    }
}




