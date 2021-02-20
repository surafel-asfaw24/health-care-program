package com.collabera.hcp.service;

import com.collabera.hcp.model.Enrollee;

public interface IEnrolleeService {
    Enrollee addEnrollee(Enrollee enrollee);
    Enrollee modifyEnrollee(Enrollee enrollee);
    int removeEnrollee(int id);
}
