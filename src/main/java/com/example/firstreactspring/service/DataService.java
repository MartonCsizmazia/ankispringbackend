package com.example.firstreactspring.service;

import com.example.firstreactspring.model.DataModel;
import com.example.firstreactspring.repository.DataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {

    @Autowired
    DataRepository dataRepository;

    public void saveData(DataModel newData) {
            // Save the new DataModel to the database
            dataRepository.save(newData);
    }

    public List<DataModel> getAllData() {
        // Return all the stored data from the database
        return dataRepository.findAll();
    }

    @Transactional  //we dont have to use queries from the repository
                    // (the data entity goes into managed state)
    public void updateData(Long id, DataModel newData) {
            DataModel data = dataRepository.findById(id).get();
            //with @Transactional, this is enough
            data.setData(newData.getData());
        // Save the updated DataModel to the database, without @Transactional
        // dataRepository.save(data);
    }

    public void deleteData(Long id) {
            // Delete the DataModel from the database
            dataRepository.deleteById(id);
    }
}
