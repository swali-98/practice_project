package com.patient.system.controller;

import com.patient.system.dtos.PatientDto;
import com.patient.system.entity.Patient;
import com.patient.system.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient")
    public ResponseEntity<?> addNewPatient(@RequestBody Patient patient){
        patientService.addNewPatient(patient);

        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable(name = "id")Long id){
       Patient patient= patientService.getPatientById(id);

       return new ResponseEntity<>(patient,HttpStatus.OK);
    }
     @DeleteMapping("/patient/{id}")
    public ResponseEntity<Patient> deletePatientById(@PathVariable(name = "id")Long id){
      return new ResponseEntity<>(patientService.deleteById(id),HttpStatus.OK);
    }
}
