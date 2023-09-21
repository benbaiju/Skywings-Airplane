package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmployeeInterfaceData {
    ResponseEntity<String> saveUser(@RequestBody EmployeeInformation user);
}
