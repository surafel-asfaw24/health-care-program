package com.collabera.hcp.controller;

import com.collabera.hcp.model.ApiResponse;
import com.collabera.hcp.model.Dependent;
import com.collabera.hcp.model.Enrollee;
import com.collabera.hcp.service.IEnrolleeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Adds Enrollee", response = Enrollee.class)
    public ResponseEntity<Enrollee> addEnrollee(@Valid @RequestBody Enrollee enrollee) {
        logger.trace("Add Enrollee controller invoked!");
        return new ResponseEntity<>(enrolleeService.addEnrollee(enrollee), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Modifies Enrollee", response = Enrollee.class)
    public ResponseEntity<Enrollee> modifyEnrollee(@Valid @RequestBody Enrollee enrollee) {
        logger.info("Modify Enrollee controller invoked!");
        return new ResponseEntity<>(enrolleeService.modifyEnrollee(enrollee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Removes Enrollee", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> removeEnrollee(@ApiParam(value = "Id of Enrollee", required = true) @PathVariable("id") int id) {
        logger.info("Remove Enrollee controller invoked!");
        return new ResponseEntity<>(ApiResponse.builder().message("Successfully deleted: " + enrolleeService.removeEnrollee(id)).build(), HttpStatus.OK);
    }

    @PostMapping("/{id}/dependent")
    @ApiOperation(value = "Adds Dependent", response = Enrollee.class)
    public ResponseEntity<Enrollee> addDependent(@ApiParam(value = "Id of Enrollee", required = true) @PathVariable("id") int id, @Valid @RequestBody Dependent dependent) {
        logger.info("Add Dependent Controller invoked!");
        return new ResponseEntity<>(enrolleeService.addDependent(dependent, id), HttpStatus.OK);
    }

    @PutMapping("/{id}/dependent")
    @ApiOperation(value = "Removes Depdendent", response = Enrollee.class)
    public ResponseEntity<Enrollee> removeDependent(@ApiParam(value = "Id of Enrollee", required = true) @PathVariable("id") int id, @Valid @RequestBody Dependent dependent) {
        logger.info("Remove Dependent Controller invoked!");
        return new ResponseEntity<>(enrolleeService.modifyDependent(dependent, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}/dependent")
    @ApiOperation(value = "Modifies Dependent", response = Enrollee.class)
    public ResponseEntity<Enrollee> modifyDependent(@ApiParam(value = "Id of Enrollee", required = true) @PathVariable("id") int id, @ApiParam(value = "Id of Enrollee", required = true) @Valid @RequestBody Dependent dependent) {
        logger.info("Modify Dependent Controller invoked!");
        return new ResponseEntity<>(enrolleeService.removeDependent(dependent, id), HttpStatus.OK);
    }
}
