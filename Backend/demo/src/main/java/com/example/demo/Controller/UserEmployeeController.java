package com.example.demo.Controller;

import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Publish.PublishController;
import com.example.demo.Repo.EmployeeInformationRepo;
import com.example.demo.Repo.UserEmployeeCombinedRepository;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class UserEmployeeController {
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

    public UserEmployeeController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    @GetMapping("/viewData")
    public List<UserEmployeeCombined> viewAllUserEmployeeData() {
        //userEmployeeService.saveDataFromSQLQuery();
        return userEmployeeService.getAllUserEmployeeData();
    }
}
