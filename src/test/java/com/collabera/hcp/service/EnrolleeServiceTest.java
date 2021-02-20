package com.collabera.hcp.service;

import com.collabera.hcp.model.Dependent;
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

    @Test
    public void saveDependent_shouldReturn() {
        Dependent dependent = Dependent.builder()
                .name("kirubel asfaw")
                .birthDate(new Date())
                .build();

        Enrollee enrollee = Enrollee.builder()
                .id(1)
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .dependents(new ArrayList<>())
                .build();

        when(enrolleeRepository.save(any(Enrollee.class)))
                .thenReturn(enrollee);
        when(enrolleeRepository.findById(any(Integer.class)))
                .thenReturn(Optional.of(enrollee));

        Enrollee actual = enrolleeService.addDependent(dependent, 1);

        assertThat(actual.getDependents().size()).isEqualTo(1);
        assertThat(actual.getDependents().get(0)).isEqualTo(dependent);
    }

    @Test
    public void updateDependent_shouldReturn() {
        Dependent dependent = Dependent.builder()
                .id(2)
                .name("kirubel asfaw")
                .birthDate(new Date())
                .build();

        Enrollee enrollee = Enrollee.builder()
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .dependents(Arrays.asList(Dependent.builder()
                        .id(2)
                        .name("asfaw asfaw")
                        .birthDate(new Date())
                        .build()))
                .build();

        when(enrolleeRepository.findById(Mockito.any(Integer.class)))
                .thenReturn(Optional.of(enrollee));
        when(enrolleeRepository.save(Mockito.any(Enrollee.class)))
                .thenReturn(enrollee);

        Enrollee actual = enrolleeService.modifyDependent(dependent, 1);

        verify(enrolleeRepository, times(1)).save(Mockito.any(Enrollee.class));
        assertThat(actual.getDependents().size()).isEqualTo(1);
        assertThat(actual.getDependents().get(0).getName()).isEqualTo("kirubel asfaw");
    }

    @Test
    public void deleteDependent_shouldDelete() {
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
