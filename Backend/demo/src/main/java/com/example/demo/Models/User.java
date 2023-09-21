package com.example.demo.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "suffix")
//    private String suffix;
//
//    @Column(name = "firstname")
//    private String firstname;
//
//    @Column(name = "middlename")
//    private String middlename;
//
//    @Column(name = "lastname")
//    private String lastname;
//
//    @Column(name = "gender")
//    private String gender;
//
//    @Column(name="dob")
//    private LocalDate dob;
//
//    @Column(name = "role")
//    private String role;
//
//    @Column(name = "location")
//    private String location;
//
//    @Column(name = "number")
//    private String number;
//
//    @Column(name = "email")
//    private String email;
//
//    @Column(name = "address")
//    private String address;
//
//    @Column(name = "city")
//    private String city;
//
//    @Column(name = "state")
//    private String state;
//
//    @Column(name = "zip")
//    private String zip;
//
//    @Column(name = "comments")
//    private String comments;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

    @NotBlank(message = "Suffix is required")
    @Size(max = 10, message = "Suffix must be at most 10 characters")
    private String suffix;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    private String firstname;

    @Size(max = 50, message = "Middle name must be at most 50 characters")
    private String middlename;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    private String lastname;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Invalid gender")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotBlank(message = "Role is required")
    @Size(max = 50, message = "Role must be at most 50 characters")
    private String role;

    @NotBlank(message = "Location is required")
    @Size(max = 100, message = "Location must be at most 100 characters")
    private String location;

    @NotBlank(message = "Number is required")
    @Size(max = 20, message = "Number must be at most 20 characters")
    private String number;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must be at most 100 characters")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(max = 100, message = "Address must be at most 100 characters")
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must be at most 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State must be at most 50 characters")
    private String state;

    @NotBlank(message = "ZIP code is required")
    @Pattern(regexp = "^\\d{6}$", message = "Invalid ZIP code format (must be 5 digits)")
    private String zip;

    @Size(max = 200, message = "Comments must be at most 200 characters")
    private String comments;

    // Getters and setters...

    // Other methods...




//    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private EmployeeInformation employee;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private EmployeeInformation employeeInformation;

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

//    public EmployeeInformation getEmployeeInformation() {
//        return employeeInformation;
//    }

//    public void setEmployeeInformation(EmployeeInformation employeeInformation) {
//        this.employeeInformation = employeeInformation;
//        if (employeeInformation != null) {
//            employeeInformation.setUser(this);
//        }
//    }

    // You can also add a static method for deserialization from JSON
    public static User fromJson(String json) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, User.class);
    }

    // You can also add a method to serialize the object to JSON
    public String toJson() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return objectMapper.writeValueAsString(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", suffix='" + suffix + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", role='" + role + '\'' +
                ", location='" + location + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
