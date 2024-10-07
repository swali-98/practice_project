package com.patient.system.exceptions;

public class BillPendingExeception extends RuntimeException{

    public BillPendingExeception(String message){
        super(message);
    }
}
