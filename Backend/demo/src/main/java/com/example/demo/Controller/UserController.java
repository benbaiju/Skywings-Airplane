package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.User;
import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Publish.PublishController;
import com.example.demo.Repo.EmployeeInformationRepo;
import com.example.demo.Repo.UserEmployeeCombinedRepository;
import com.example.demo.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController implements UserInterface,EmployeeInterfaceData {

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

    @Value("${activemq.queue.name}")
    private String dataQueueName;

    public UserController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
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

    @PostMapping( value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {


            jmsTemplate.convertAndSend(dataQueueName, user);


            return ResponseEntity.ok("{\"message\": \"User saved successfully\"}");

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String saveDataFromSQLQuery() {
        userEmployeeService.saveDataFromSQLQuery();
        return "Data saved successfully!";
    }

    @PutMapping(value="update/{id}")
    public String updateUser(@PathVariable long id,@RequestBody User user){
        User updatedUser= userRepo.findById(id).get();
        updatedUser.setSuffix(user.getSuffix());
        updatedUser.setFirstname(user.getFirstname());
        updatedUser.setMiddlename(user.getMiddlename());
        updatedUser.setLastname(user.getLastname());
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

    public String updateEmployeeUser(@PathVariable long id,@RequestBody User user){

        UserEmployeeCombined userEmployee = repository.findById(id).get();
        userEmployee.setSuffix(user.getSuffix());
        userEmployee.setFirstname(user.getFirstname());
        userEmployee.setMiddlename(user.getMiddlename());
        userEmployee.setLastname(user.getLastname());
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
    @CrossOrigin
    @PostMapping(value = "/saveemployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveUser(@RequestBody EmployeeInformation user) {
        try {

            employeeRepo.save(user);
            saveDataFromSQLQuery();
            return ResponseEntity.ok("{\"message\": \"Employee Details saved successfully\"}");

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
