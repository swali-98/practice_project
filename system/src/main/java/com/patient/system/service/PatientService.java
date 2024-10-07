package com.patient.system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patient.system.dtos.Datadto;
import com.patient.system.dtos.ResponseDto;
import com.patient.system.entity.Patient;
import com.patient.system.exceptions.BillPendingExeception;
import com.patient.system.exceptions.PatientNotFoundException;
import com.patient.system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private WebHookService webHookService;
    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
        Datadto datadto=new Datadto();
        datadto.setEvent("create");
        datadto.setPatient(patient);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String jsonString=objectMapper.writeValueAsString(datadto);
            webHookService.executeWebhook(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    public Patient getPatientById(Long id) {

      Patient patient=  patientRepository.findById(id);
      if(patient==null){
          throw new PatientNotFoundException("patient is not present with this id");
      }
      return patient;
    }

    public Patient deleteById(Long id) {
        Patient findPatient=patientRepository.findById(id);
        if(findPatient==null){
            throw new RuntimeException("patient is not found");
        }
        Datadto datadto=new Datadto();
        datadto.setEvent("delete");
        datadto.setPatient(findPatient);
        ObjectMapper objectMapper=new ObjectMapper();
        try {
            String jsonString=objectMapper.writeValueAsString(datadto);
          ResponseDto responseDto= webHookService.executeWebhook(jsonString);
          if(responseDto.getStatus()==false){
             throw new BillPendingExeception("some billing is pending with this customer");
          }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

      return  patientRepository.deleteById(id);

    }
}
