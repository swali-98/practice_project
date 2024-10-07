package com.patient.system.repository;

import com.patient.system.entity.Patient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PatientRepostioryImpl implements PatientRepository{

    private Map<Long,Patient> patientMap=new HashMap<>();
    @Override
    public void save(Patient patient) {
        Long id=patient.getPatientId();
        patientMap.put(id,patient);

    }

    @Override
    public Patient findById(Long id) {
        return patientMap.get(id);
    }

    @Override
    public Patient deleteById(Long id) {
      Patient patient=  patientMap.remove(id);

      return patient;
    }
}
