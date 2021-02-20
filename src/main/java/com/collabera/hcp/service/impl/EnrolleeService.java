package com.collabera.hcp.service.impl;

import com.collabera.hcp.exception.ResourceNotFoundException;
import com.collabera.hcp.model.Enrollee;
import com.collabera.hcp.repository.IEnrolleeRepository;
import com.collabera.hcp.service.IEnrolleeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrolleeService implements IEnrolleeService {
    private final IEnrolleeRepository enrolleeRepository;
    private Logger logger = LoggerFactory.getLogger(EnrolleeService.class);

    @Autowired
    public EnrolleeService(IEnrolleeRepository enrolleeRepository) {
        this.enrolleeRepository = enrolleeRepository;
    }

    @Override
    public Enrollee addEnrollee(Enrollee enrollee) {
        logger.trace("Add enrollee service invoked!");
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Enrollee modifyEnrollee(Enrollee enrollee) {
        logger.trace("Modify enrollee service invoked!");
        Enrollee _enrollee = enrolleeRepository.findById(enrollee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Enrollee not found!"));

        _enrollee.setName(enrollee.getName());
        _enrollee.setPhoneNumber(enrollee.getPhoneNumber());
        _enrollee.setStatus(enrollee.getStatus());
        _enrollee.setBirthDate(enrollee.getBirthDate());
        _enrollee.setDependents(enrollee.getDependents());

        return enrolleeRepository.save(_enrollee);
    }

    @Override
    public int removeEnrollee(int id) {
        logger.trace("Remove enrollee service invoked!");
        enrolleeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollee not found!"));

        enrolleeRepository.deleteById(id);

        return id;
    }
}
