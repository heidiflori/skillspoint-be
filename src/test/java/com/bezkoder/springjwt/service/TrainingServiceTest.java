package com.bezkoder.springjwt.service;

import com.bezkoder.springjwt.models.Training;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.TrainingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrainingServiceTest {
    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;

//    @Test
//    void givenTrainingThenSave() {
//        User user = new User();
//        Training trainingToSave = new Training();
//        trainingToSave.setId(1);
//        trainingToSave.setTitle("titleToSave");
//        trainingToSave.setTrainer("trainername");
//        trainingToSave.setDescription("description");
//        trainingToSave.setOccupiedSlots(2);
//        trainingToSave.setMaximumSlots(5);
//        trainingToSave.setType("technical");
//        trainingToSave.setStartingDate(new Date());
//        trainingToSave.setDuration(120.00);
//        trainingToSave.setStatus("ongoing");
//        trainingToSave.setAdminApproval("pending");
//        trainingToSave.setReviews(Collections.emptySet());
//        trainingToSave.setEnrolledUsers(Collections.emptySet());
//
//        Mockito.when(trainingRepository.save(trainingToSave)).thenReturn(Optional.of(trainingToSave));
//        trainingService.save(trainingToSave);
//
//        assertEquals(trainingToSave, trainingService.save(trainingToSave), "trainingSaved is different than trainingToSave");
//
//    }

    @Test
    void givenAdminApprovalPendingThenReturnPendingTrainings() {
        String pending = "pending";
        String approved = "approved";
        List<Training> expectedTrainings = new ArrayList<>();

        Training expectedTraining1 = new Training();
        expectedTraining1.setAdminApproval(pending);
        expectedTrainings.add(expectedTraining1);

        Training expectedTraining2 = new Training();
        expectedTraining2.setAdminApproval(pending);
        expectedTrainings.add(expectedTraining2);

        Training expectedTraining3 = new Training();
        expectedTraining3.setAdminApproval(approved);

        Mockito.when(trainingRepository.findByAdminApprovalIsPending()).thenReturn(expectedTrainings);
        List<Training> actualTrainings = trainingService.getPendingTrainings();

        assertEquals(expectedTrainings, actualTrainings, "list of trainings not found");
    }


}