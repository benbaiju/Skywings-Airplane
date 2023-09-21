package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Publish.PublishController;
import com.example.demo.Repo.EmployeeInformationRepo;
import com.example.demo.Repo.UserEmployeeCombinedRepository;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@CrossOrigin
@RestController
public class EmployeeController implements EmployeeInterface {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmployeeInformationRepo employeeRepo;


    @Autowired
    private PublishController publishController;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private UserEmployeeCombinedRepository repository;

    @Autowired
    private UserEmployeeService userEmployeeService;

    public EmployeeController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }



    @PutMapping(value = "updateEmployee/{id}")
    public String updateEmployee(@PathVariable long id, @RequestBody EmployeeInformation employeeInfo) {
        EmployeeInformation updatedEmployee = employeeRepo.findById(id).get();

        updatedEmployee.setDateOfJoining(employeeInfo.getDateOfJoining());
        updatedEmployee.setYearsOfExperience(employeeInfo.getYearsOfExperience());
        updatedEmployee.setRelevantCertifications(employeeInfo.getRelevantCertifications());
        updatedEmployee.setRole(employeeInfo.getRole());
        updatedEmployee.setLocation(employeeInfo.getLocation());
        updatedEmployee.setSalary(employeeInfo.getSalary());
        updatedEmployee.setIsFullTime(employeeInfo.getIsFullTime());
        updateUserEmployee(id,employeeInfo);
        employeeRepo.save(updatedEmployee);
        return "Employee updated";
    }
    public String updateUserEmployee(@PathVariable long id, @RequestBody EmployeeInformation employeeInfo) {

        UserEmployeeCombined userEmployee = repository.findById(id).get();
        userEmployee .setDateOfJoining(employeeInfo.getDateOfJoining());
        userEmployee .setYearsOfExperience(employeeInfo.getYearsOfExperience());
        userEmployee .setRelevantCertifications(employeeInfo.getRelevantCertifications());
        userEmployee .setEmployeeRole(employeeInfo.getRole());
        userEmployee .setEmployeeLocation(employeeInfo.getLocation());
        userEmployee .setSalary(employeeInfo.getSalary());
        userEmployee .setIsFullTime(employeeInfo.getIsFullTime());

        repository.save(userEmployee );
        return "Employee updated";
    }

    @GetMapping(value="/employeeusers")
    public List<EmployeeInformation> getEmployeeInformation(){
        return employeeRepo.findAll();
    }

}
