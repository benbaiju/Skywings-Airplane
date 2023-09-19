package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Publish.PublishController;
import com.example.demo.Repo.EmployeeInformationRepo;
import com.example.demo.Repo.UserEmployeeCombinedRepository;
import com.example.demo.Repo.UserRepo;
//import com.example.demo.consumer.MessageDatabase;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class Apicontrollers {
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
//
//    @GetMapping(value="/combined")
//    public List<UserEmployeeCombined> getAllUserEmployeeData() {
//        return repository.getUserEmployeeData();
//    }

    @Autowired
    private UserEmployeeService userEmployeeService;

    //@GetMapping("/saveData")
    public String saveDataFromSQLQuery() {
        userEmployeeService.saveDataFromSQLQuery();
        return "Data saved successfully!";
    }
    @GetMapping("/viewData")
    public List<UserEmployeeCombined> viewAllUserEmployeeData() {
        userEmployeeService.saveDataFromSQLQuery();
        return userEmployeeService.getAllUserEmployeeData();
    }
    @Value("${activemq.queue.name}")
    private String dataQueueName;


    @GetMapping(value="/")
    public String getPage(){
        return "Welcome";
    }

    @GetMapping(value="/users")
    public List<User> getUser(){
        return userRepo.findAll();
    }

    @GetMapping(value = "/usersByLocation/{location}")
    public List<User> getUsersByLocation(@PathVariable String location) {
        System.out.println("Searching for users in location: " + location);
        List<User> users = userRepo.findByLocation(location);
        System.out.println("Found " + users.size() + " users.");
        //return users;
        return userRepo.findByLocation(location);
    }

    public Apicontrollers(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @CrossOrigin
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {

            //jmsTemplate.convertAndSend("DataQueue", user);
            jmsTemplate.convertAndSend(dataQueueName, user);

            //userRepo.save(user);
            return ResponseEntity.ok("{\"message\": \"User saved successfully\"}");
//
//            //return new ResponseEntity<>("Sent.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @PostMapping(value = "/saveemployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody EmployeeInformation user) {
        try {

            //jmsTemplate.convertAndSend("DataQueue", user);
//            jmsTemplate.convertAndSend(dataQueueName, user);

            employeeRepo.save(user);
            saveDataFromSQLQuery();
            return ResponseEntity.ok("{\"message\": \"Employee Details saved successfully\"}");
//
//            //return new ResponseEntity<>("Sent.", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PostMapping(value="/save")
//    public void saveUser(@RequestBody User user){
//        userRepo.save(user);
//        //return "Saved...";
//    }

    @PutMapping(value="update/{id}")
    public String updateUser(@PathVariable long id,@RequestBody User user){
        User updatedUser= userRepo.findById(id).get();
        updatedUser.setSuffix(user.getSuffix());
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setMiddlename(user.getMiddlename());
        updatedUser.setLastname(user.getLastname());
        //updatedUser.setSelectedDate(user.getSelectedDate());
        updatedUser.setNumber(user.getNumber());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setCity(user.getCity());
        updatedUser.setState(user.getState());
        updatedUser.setZip(user.getZip());
        updatedUser.setComments(user.getComments());
        userRepo.save(updatedUser);
        return "updated";
    }
    @DeleteMapping (value="delete/{id}")
    public String deleteUser(@PathVariable long id){
        User updatedUser= userRepo.findById(id).get();
        EmployeeInformation Employee=employeeRepo.findById(id).get();
        userRepo.delete(updatedUser);
        employeeRepo.delete(Employee);
        return "Deleted";
    }


}
