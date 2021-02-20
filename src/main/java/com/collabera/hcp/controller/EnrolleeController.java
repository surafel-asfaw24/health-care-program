package com.collabera.hcp.controller;

import com.collabera.hcp.model.ApiResponse;
import com.collabera.hcp.model.Enrollee;
import com.collabera.hcp.service.IEnrolleeService;
import com.collabera.hcp.service.impl.EnrolleeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/enrollee")
public class EnrolleeController {
    private final IEnrolleeService enrolleeService;
    private Logger logger = LoggerFactory.getLogger(EnrolleeController.class);

    @Autowired
    public EnrolleeController(IEnrolleeService enrolleeService) {
        this.enrolleeService = enrolleeService;
    }

    @PostMapping
    public ResponseEntity<Enrollee> addEnrollee(@Valid @RequestBody Enrollee enrollee) {
        logger.trace("Add Enrollee controller invoked!");
        return new ResponseEntity<>(enrolleeService.addEnrollee(enrollee), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Enrollee> modifyEnrollee(@Valid @RequestBody Enrollee enrollee) {
        logger.info("Modify Enrollee controller invoked!");
        return new ResponseEntity<>(enrolleeService.modifyEnrollee(enrollee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> removeEnrollee(@PathVariable int id) {
        logger.info("Remove Enrollee controller invoked!");
        return new ResponseEntity<>(ApiResponse.builder().message("Successfully deleted: " + enrolleeService.removeEnrollee(id)).build(), HttpStatus.OK);
    }
}
