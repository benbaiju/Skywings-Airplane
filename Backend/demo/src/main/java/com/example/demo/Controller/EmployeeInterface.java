package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmployeeInterface {
     ResponseEntity<String> saveUser(@RequestBody EmployeeInformation user);
     String updateEmployee(@PathVariable long id, @RequestBody EmployeeInformation employeeInfo);
}
