package com.patient.system.repository;

import com.patient.system.entity.Patient;

public interface PatientRepository {

    public void save(Patient patient);

    public Patient findById(Long id);

    public Patient deleteById(Long id);


}
