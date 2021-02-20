package com.collabera.hcp.service;

import com.collabera.hcp.model.Dependent;
import com.collabera.hcp.model.Enrollee;

public interface IEnrolleeService {
    Enrollee addEnrollee(Enrollee enrollee);
    Enrollee modifyEnrollee(Enrollee enrollee);
    int removeEnrollee(int id);
    Enrollee addDependent(Dependent dependent, int id);
    Enrollee modifyDependent(Dependent dependent, int id);
    Enrollee removeDependent(Dependent dependent, int id);
}
