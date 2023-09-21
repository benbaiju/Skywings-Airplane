package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.User;
import com.example.demo.Models.UserEmployeeCombined;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeUser {
     List<UserEmployeeCombined> viewAllUserEmployeeData();
     String updateEmployeeUser(@PathVariable long id, @RequestBody User user);
     String updateUserEmployee(@PathVariable long id, @RequestBody EmployeeInformation employeeInfo);
}
