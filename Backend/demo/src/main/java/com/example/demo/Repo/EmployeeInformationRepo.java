package com.example.demo.Repo;

import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeInformationRepo extends JpaRepository<EmployeeInformation,Long> {
}
