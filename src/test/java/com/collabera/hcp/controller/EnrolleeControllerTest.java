package com.collabera.hcp.controller;

import com.collabera.hcp.model.Dependent;
import com.collabera.hcp.model.Enrollee;
import com.collabera.hcp.service.IEnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EnrolleeController.class)
public class EnrolleeControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private IEnrolleeService enrolleeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void post_ShouldAddEnrollee() throws Exception {
        Enrollee enrollee = Enrollee.builder()
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .build();

        String enrolleeJSON = objectMapper.writeValueAsString(enrollee);


        when(enrolleeService.addEnrollee(Mockito.any(Enrollee.class)))
                .thenReturn(enrollee);

        String result = mockMVC.perform(post("/api/enrollee").content(enrolleeJSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(enrolleeJSON, result, false);
    }

    @Test
    public void put_ShouldUpdateEnrollee() throws Exception {
        Enrollee enrollee = Enrollee.builder()
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .build();

        String enrolleeJSON = objectMapper.writeValueAsString(enrollee);

        when(enrolleeService.modifyEnrollee(Mockito.any(Enrollee.class)))
                .thenReturn(enrollee);

        mockMVC
                .perform(put("/api/enrollee").contentType(MediaType.APPLICATION_JSON).content(enrolleeJSON))
                .andExpect(status().isOk());
    }

    @Test
    public void delete_ShouldDeleteEnrollee() throws Exception {
        when(enrolleeService.removeEnrollee(Mockito.any(Integer.class)))
                .thenReturn(1);

        mockMVC
                .perform(delete("/api/enrollee/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void post_ShouldAddDependent() throws Exception {
        Dependent dependent = Dependent.builder()
                .id(2)
                .name("kirubel asfaw")
                .birthDate(new Date())
                .build();

        Enrollee enrollee = Enrollee.builder()
                .id(1)
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .dependents(Arrays.asList(dependent))
                .build();

        String dependentJSON = objectMapper.writeValueAsString(dependent);
        String enrolleeJSON = objectMapper.writeValueAsString(enrollee);

        when(enrolleeService.addDependent(Mockito.any(Dependent.class), Mockito.any(Integer.class)))
                .thenReturn(enrollee);

        String result = mockMVC.perform(post("/api/enrollee/1/dependent")
                .content(dependentJSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(enrolleeJSON, result, false);
    }

    @Test
    public void put_ShouldUpdateDependent() throws Exception {
        Dependent dependent = Dependent.builder()
                .id(2)
                .name("kirubel asfaw")
                .birthDate(new Date())
                .build();

        Enrollee enrollee = Enrollee.builder()
                .id(1)
                .name("surafel asfaw")
                .birthDate(new Date())
                .phoneNumber("240-418-1349")
                .status(true)
                .dependents(Arrays.asList(dependent))
                .build();

        String dependentJSON = objectMapper.writeValueAsString(dependent);

        when(enrolleeService.modifyEnrollee(Mockito.any(Enrollee.class)))
                .thenReturn(enrollee);

        mockMVC.perform(put("/api/enrollee/1/dependent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dependentJSON))
                .andExpect(status().isOk());

        Dependent dependent2 = Dependent.builder()
                .id(2)
                .birthDate(new Date())
                .build();

        String dependentJSON2 = objectMapper.writeValueAsString(dependent2);

        mockMVC.perform(put("/api/enrollee/1/dependent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dependentJSON2))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delete_ShouldDeleteDependent() throws Exception {
        when(enrolleeService.removeEnrollee(Mockito.any(Integer.class)))
                .thenReturn(1);

        mockMVC.perform(delete("/api/enrollee/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
