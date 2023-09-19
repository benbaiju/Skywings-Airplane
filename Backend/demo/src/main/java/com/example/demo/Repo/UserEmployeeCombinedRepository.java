package com.example.demo.Repo;
import com.example.demo.Models.UserEmployeeCombined;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmployeeCombinedRepository extends JpaRepository<UserEmployeeCombined, Long> {
}


//import com.example.demo.Models.UserEmployeeCombined;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import java.util.List;
//
//public interface UserEmployeeCombinedRepository extends JpaRepository<UserEmployeeCombined, Long> {
//
//    @Query(value = "SELECT " +
//            "u.id AS id, " +
//            "u.suffix AS suffix, " +
//            "u.firstname AS firstname, " +
//            "u.middlename AS middlename, " +
//            "u.lastname AS lastname, " +
//            "u.gender AS gender, " +
//            "u.dob AS dob, " +
//            "u.role AS role, " +
//            "u.location AS location, " +
//            "u.number AS number, " +
//            "u.email AS email, " +
//            "u.address AS address, " +
//            "u.city AS city, " +
//            "u.state AS state, " +
//            "u.zip AS zip, " +
//            "u.comments AS comments, " +
//            "e.date_of_joining AS dateOfJoining, " +
//            "e.years_of_experience AS yearsOfExperience, " +
//            "e.relevant_certifications AS relevantCertifications, " +
//            "e.role AS employeeRole, " +
//            "e.location AS employeeLocation, " +
//            "e.salary AS salary, " +
//            "e.is_full_time AS isFullTime " +
//            "FROM user u " +  // Use lowercase table names
//            "JOIN employee_information e ON u.id = e.eid", nativeQuery = true)
//    List<UserEmployeeCombined> getUserEmployeeData();
//}
