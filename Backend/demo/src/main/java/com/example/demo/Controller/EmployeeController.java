//package com.example.demo.Controller;
//
//import com.example.demo.Models.EmployeeInformation;
//import com.example.demo.Models.User;
//import com.example.demo.Repo.EmployeeInformationRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin
//@RestController
//public class EmployeeController {
//
//    @Autowired
//    private EmployeeInformationRepo employeeRepo;
//
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//
//    public EmployeeController(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    @CrossOrigin
//    @PostMapping(value = "/saveemployee", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> saveUser(@RequestBody EmployeeInformation user) {
//        try {
//
//            //jmsTemplate.convertAndSend("DataQueue", user);
////            jmsTemplate.convertAndSend(dataQueueName, user);
//
//            employeeRepo.save(user);
//            return ResponseEntity.ok("{\"message\": \"User saved successfully\"}");
////
////            //return new ResponseEntity<>("Sent.", HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//}
