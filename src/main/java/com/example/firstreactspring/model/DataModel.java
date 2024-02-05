package com.example.firstreactspring.model;

import com.example.firstreactspring.repository.DataRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long position;
    private String data;

    public void setPositionAutomatically(DataRepository dataModelRepository) {
        // Fetch the maximum position from the database
        Long maxPosition = dataModelRepository.findMaxPosition();

        // Increment the maximum position by 1 or set to 1 if no records exist
        this.position = (maxPosition != null) ? maxPosition + 1 : 1;
    }

    // Example method to update positions after deletion
    public static void updatePositions(DataRepository dataModelRepository) {
        List<DataModel> orderedDataList = dataModelRepository.findAllOrderedByPosition();

        for (int i = 0; i < orderedDataList.size(); i++) {
            DataModel data = orderedDataList.get(i);
            Long newPosition = (long) (i + 1);

            // Update the position
            dataModelRepository.updatePositionById(data.getId(), newPosition);
        }
    }


}