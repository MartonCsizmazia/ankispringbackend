package com.example.firstreactspring.repository;

import com.example.firstreactspring.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRepository extends JpaRepository<DataModel, Long> {
    // Additional query methods, if needed
    List<DataModel> findByDataContaining(String value);
}