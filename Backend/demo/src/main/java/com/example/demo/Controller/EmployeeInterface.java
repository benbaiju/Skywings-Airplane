package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.UserEmployeeCombined;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeInterface {
     List<EmployeeInformation> getEmployeeInformation();
     String updateEmployee(@PathVariable long id, @RequestBody EmployeeInformation employeeInfo);
}
