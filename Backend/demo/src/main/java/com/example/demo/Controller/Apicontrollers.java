package com.example.demo.Controller;

import com.example.demo.Models.User;
import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Publish.PublishController;
import com.example.demo.Repo.EmployeeInformationRepo;
import com.example.demo.Repo.UserEmployeeCombinedRepository;
import com.example.demo.Repo.UserRepo;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class Apicontrollers implements EmployeeUser,UserInterface,EmployeeInterface {
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
        //userEmployeeService.saveDataFromSQLQuery();
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

    @GetMapping(value="/employeeusers")
    public List<EmployeeInformation> getEmployeeInformation(){
        return employeeRepo.findAll();
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

//    @CrossOrigin
//    @PostMapping( value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // If there are validation errors, build a response with error messages
//            Map<String, String> errorMap = new HashMap<>();
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//
//                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
//            }
//            return ResponseEntity.badRequest().body(errorMap);
//        }
//
//        try {
//            jmsTemplate.convertAndSend(dataQueueName, user);
//            // userRepo.save(user); // Commented out for demonstration purposes
//            return ResponseEntity.ok("{\"message\": \"User saved successfully\"}");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }
    @PostMapping( value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
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
        updatedUser.setRole(user.getRole());
        updatedUser.setLocation(user.getLocation());
        updatedUser.setNumber(user.getNumber());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAddress(user.getAddress());
        updatedUser.setCity(user.getCity());
        updatedUser.setState(user.getState());
        updatedUser.setZip(user.getZip());
        updatedUser.setComments(user.getComments());
        userRepo.save(updatedUser);
        updateEmployeeUser(id,user);
        return "updated";
    }

    //@PutMapping(value="update/{id}")
    public String updateEmployeeUser(@PathVariable long id,@RequestBody User user){
        //User updatedUser= userRepo.findById(id).get();
        UserEmployeeCombined userEmployee = repository.findById(id).get();
        userEmployee.setSuffix(user.getSuffix());
        userEmployee.setFirstname(user.getFirstname());
        userEmployee.setMiddlename(user.getMiddlename());
        userEmployee.setLastname(user.getLastname());
        //updatedUser.setSelectedDate(user.getSelectedDate());
        userEmployee.setRole(user.getRole());
        userEmployee.setLocation(user.getLocation());
        userEmployee.setNumber(user.getNumber());
        userEmployee.setEmail(user.getEmail());
        userEmployee.setAddress(user.getAddress());
        userEmployee.setCity(user.getCity());
        userEmployee.setState(user.getState());
        userEmployee.setZip(user.getZip());
        userEmployee.setComments(user.getComments());
        repository.save(userEmployee);
        return "updated";
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
        //EmployeeInformation updatedEmployee = employeeRepo.findById(id).get();
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
//    @CrossOrigin
//    @PutMapping(value = "/updateEmployee/{id}") // Corrected endpoint path
//    public String updateEmployee(@PathVariable long id, @RequestBody EmployeeInformation employeeInfo) {
//        EmployeeInformation updatedEmployee = employeeRepo.findById(id).orElse(null);
//
//        if (updatedEmployee != null) {
//            updatedEmployee.setDateOfJoining(employeeInfo.getDateOfJoining());
//            updatedEmployee.setYearsOfExperience(employeeInfo.getYearsOfExperience());
//            updatedEmployee.setRelevantCertifications(employeeInfo.getRelevantCertifications());
//            updatedEmployee.setRole(employeeInfo.getRole());
//            updatedEmployee.setLocation(employeeInfo.getLocation());
//            updatedEmployee.setSalary(employeeInfo.getSalary());
//            updatedEmployee.setIsFullTime(employeeInfo.getIsFullTime());
//
//            employeeRepo.save(updatedEmployee);
//            return "Employee updated";
//        } else {
//            return "Employee not found"; // Handle the case where the employee with the given ID is not found
//        }
//    }
//@PutMapping(value = "updateUser/{id}")
//public String updateUser(@PathVariable Long id, @RequestBody UserEmployeeCombined userEmployeeCombined) {
//    UserEmployeeCombined updatedUserEmployeeCombined = repository.findById(id).orElse(null);
//
//    if (updatedUserEmployeeCombined != null) {
//        // Update fields from the User class
//        updatedUserEmployeeCombined.setSuffix(userEmployeeCombined.getSuffix());
//        updatedUserEmployeeCombined.setFirstname(userEmployeeCombined.getFirstname());
//        updatedUserEmployeeCombined.setMiddlename(userEmployeeCombined.getMiddlename());
//        updatedUserEmployeeCombined.setLastname(userEmployeeCombined.getLastname());
//        updatedUserEmployeeCombined.setGender(userEmployeeCombined.getGender());
//        updatedUserEmployeeCombined.setDob(userEmployeeCombined.getDob());
//        updatedUserEmployeeCombined.setRole(userEmployeeCombined.getRole());
//        updatedUserEmployeeCombined.setLocation(userEmployeeCombined.getLocation());
//        updatedUserEmployeeCombined.setNumber(userEmployeeCombined.getNumber());
//        updatedUserEmployeeCombined.setEmail(userEmployeeCombined.getEmail());
//        updatedUserEmployeeCombined.setAddress(userEmployeeCombined.getAddress());
//        updatedUserEmployeeCombined.setCity(userEmployeeCombined.getCity());
//        updatedUserEmployeeCombined.setState(userEmployeeCombined.getState());
//        updatedUserEmployeeCombined.setZip(userEmployeeCombined.getZip());
//        updatedUserEmployeeCombined.setComments(userEmployeeCombined.getComments());
//
//        repository.save(updatedUserEmployeeCombined);
//        return "User updated";
//    } else {
//        return "User not found";
//    }
//}
//
//    public String updateEmployee(@PathVariable Long id, @RequestBody UserEmployeeCombined userEmployeeCombined) {
//        UserEmployeeCombined updatedUserEmployeeCombined = repository.findById(id).orElse(null);
//
//        if (updatedUserEmployeeCombined != null) {
//            // Update fields from the EmployeeInformation class
//            updatedUserEmployeeCombined.setDateOfJoining(userEmployeeCombined.getDateOfJoining());
//            updatedUserEmployeeCombined.setYearsOfExperience(userEmployeeCombined.getYearsOfExperience());
//            updatedUserEmployeeCombined.setRelevantCertifications(userEmployeeCombined.getRelevantCertifications());
//            updatedUserEmployeeCombined.setEmployeeRole(userEmployeeCombined.getEmployeeRole());
//            updatedUserEmployeeCombined.setEmployeeLocation(userEmployeeCombined.getEmployeeLocation());
//            updatedUserEmployeeCombined.setSalary(userEmployeeCombined.getSalary());
//            updatedUserEmployeeCombined.setIsFullTime(userEmployeeCombined.getIsFullTime());
//
//            repository.save(updatedUserEmployeeCombined);
//            return "Employee updated";
//        } else {
//            return "Employee not found";
//        }
//    }



    @DeleteMapping (value="delete/{id}")
    public String deleteUser(@PathVariable long id){
        User updatedUser= userRepo.findById(id).get();
        EmployeeInformation Employee=employeeRepo.findById(id).get();
        UserEmployeeCombined UserEmployee=repository.findById(id).get();
        userRepo.delete(updatedUser);
        employeeRepo.delete(Employee);
        repository.delete(UserEmployee);
        return "Deleted";
    }




}
