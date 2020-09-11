package com.paypal.bfs.test.error;

public class EmployeeNotFoundException  extends  RuntimeException{

    public EmployeeNotFoundException(String id){
        super("Employee ID: ".concat(id).concat(" not found!!"));
    }
}
