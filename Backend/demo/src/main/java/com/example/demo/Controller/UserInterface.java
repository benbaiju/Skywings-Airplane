package com.example.demo.Controller;

import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface UserInterface {
    List<User> getUser();
    List<User> getUsersByLocation(@PathVariable String location);
    //ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult bindingResult);
    ResponseEntity<String> saveUser(@RequestBody User user);
    String updateUser(@PathVariable long id,@RequestBody User user);
    String deleteUser(@PathVariable long id);
}
