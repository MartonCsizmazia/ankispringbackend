package com.example.firstreactspring.repository;

import com.example.firstreactspring.model.DataModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DataRepository extends JpaRepository<DataModel, Long> {
    // Additional query methods, if needed
    List<DataModel> findByDataContaining(String value);

    @Query("SELECT MAX(d.position) FROM DataModel d")
    Long findMaxPosition();

    @Query("SELECT d FROM DataModel d ORDER BY d.position")
    List<DataModel> findAllOrderedByPosition();

    @Modifying
    @Transactional
    @Query("UPDATE DataModel d SET d.position = :newPosition WHERE d.id = :id")
    void updatePositionById(@Param("id") Long id, @Param("newPosition") Long newPosition);
}