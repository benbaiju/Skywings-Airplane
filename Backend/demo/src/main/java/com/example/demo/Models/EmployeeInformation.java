package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_information")
public class EmployeeInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eid")
    private Long eid; // Primary key for EmployeeInformation

    @Column(name = "date_of_joining")
    @NotNull(message = "Date of joining is required")
    private LocalDate dateOfJoining;

    @Column(name = "years_of_experience")
    @NotBlank(message = "Years of experience is required")
    private String yearsOfExperience;

    @Column(name = "relevant_certifications")
    private String relevantCertifications;

    @Column(name = "role")
    @NotBlank(message = "Role is required")
    private String role;

    @Column(name = "location")
    @NotBlank(message = "Location is required")
    private String location;

    @Column(name = "salary")
    @Min(value = 0, message = "Salary must be a positive number")
    private double salary;

    @Column(name = "is_full_time")
    @NotBlank(message = "Full-time status is required")
    private String isFullTime;

//    @Column(name = "date_of_joining")
//    private LocalDate dateOfJoining;
//
//    @Column(name = "years_of_experience")
//    private String yearsOfExperience;
//
//    @Column(name = "relevant_certifications")
//    private String relevantCertifications;
//
//    @Column(name = "role") // Field name matches the column name
//    private String role;
//
//    @Column(name = "location") // Field name matches the column name
//    private String location;
//
//    @Column(name = "salary") // Field name matches the column name
//    private double salary;
//
//    @Column(name = "is_full_time") // Field name matches the column name
//    private String isFullTime;

//    @OneToOne
//    @JoinColumn(name="eid")
//    @MapsId
//    private User user;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "employee_user_mapping", // Name of the mapping table
//            joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "eid"),
//            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
//    )
//    private List<User> users;

    // Constructors, getters, and setters
    public EmployeeInformation() {
        // Default constructor
    }
    public EmployeeInformation(List<User> users) {

    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    // Getter for yearsOfExperience
    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    // Setter for yearsOfExperience
    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }


    public String getRelevantCertifications() {
        return relevantCertifications;
    }

    public void setRelevantCertifications(String relevantCertifications) {
        this.relevantCertifications = relevantCertifications;

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    // Getter for isFullTime
    public String getIsFullTime() {
        return isFullTime;
    }

    // Setter for isFullTime
    public void setIsFullTime(String isFullTime) {
        this.isFullTime = isFullTime;
    }

}
