package com.collabera.hcp.service.impl;

import com.collabera.hcp.exception.ResourceNotFoundException;
import com.collabera.hcp.model.Dependent;
import com.collabera.hcp.model.Enrollee;
import com.collabera.hcp.repository.IEnrolleeRepository;
import com.collabera.hcp.service.IEnrolleeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EnrolleeService implements IEnrolleeService {
    private IEnrolleeRepository enrolleeRepository;
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
        return enrolleeRepository.findById(id).map(enrollee -> {
            enrolleeRepository.deleteById(id);
            return id;
        }).orElseThrow(() -> new ResourceNotFoundException("Enrollee not found!"));
    }

    @Override
    public Enrollee addDependent(Dependent dependent, int id) {
        logger.trace("Add Dependent service invoked!");
        Enrollee enrollee = enrolleeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollee not found!"));
        enrollee.getDependents().add(dependent);
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Enrollee modifyDependent(Dependent dependent, int id) {
        logger.trace("Modify Dependent service invoked!");
        Enrollee enrollee = enrolleeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollee not found!"));
        enrollee.setDependents(enrollee.getDependents().stream().peek(_dependent -> {
            if (_dependent.getId() == dependent.getId()) {
                _dependent.setName(dependent.getName());
                _dependent.setBirthDate(dependent.getBirthDate());
            }
        }).collect(Collectors.toList()));
        return enrolleeRepository.save(enrollee);
    }

    @Override
    public Enrollee removeDependent(Dependent dependent, int id) {
        logger.trace("Remove Dependent service invoked!");
        return enrolleeRepository.findById(id).map(_enrollee -> {
            _enrollee.setDependents(_enrollee.getDependents().stream()
                    .filter(_dependent -> _dependent.getId() != dependent.getId())
                    .collect(Collectors.toList()));
            return enrolleeRepository.save(_enrollee);
        }).orElseThrow(() -> new ResourceNotFoundException("Enrollee not found!"));
    }
}
