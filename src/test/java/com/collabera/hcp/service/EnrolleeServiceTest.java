package com.collabera.hcp.service;

import com.collabera.hcp.model.Enrollee;
import com.collabera.hcp.repository.IEnrolleeRepository;
import com.collabera.hcp.service.impl.EnrolleeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnrolleeServiceTest {

    @Mock
    private IEnrolleeRepository enrolleeRepository;

    @InjectMocks
    private EnrolleeService enrolleeService;

    @Test
    public void saveEnrollee_shouldReturnTheEnrollee() {
        Enrollee enrollee = Enrollee.builder()
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .build();

        Mockito.when(enrolleeRepository.save(Mockito.any(Enrollee.class)))
                .thenReturn(enrollee);

        Enrollee Enrollee_actual = enrolleeService.addEnrollee(enrollee);

        assertThat(enrollee.getName()).isEqualTo(Enrollee_actual.getName());
    }

    @Test
    public void updateEnrollee_shouldReturnTheEnrollee() {
        Enrollee enrollee = Enrollee.builder()
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .build();

        when(enrolleeRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.of(enrollee));

        enrolleeService.modifyEnrollee(enrollee);

        verify(enrolleeRepository, times(1)).save(Mockito.any(Enrollee.class));
    }

    @Test
    public void deleteEnrollee_shouldDeleteEnrollee() {
        Enrollee enrollee = Enrollee.builder()
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .build();

        doNothing().when(enrolleeRepository).deleteById(Mockito.any(Integer.class));

        when(enrolleeRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.of(enrollee));

        enrolleeService.removeEnrollee(1);

        verify(enrolleeRepository, times(1)).deleteById(Mockito.any(Integer.class));
    }

}
