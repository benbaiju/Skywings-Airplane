package com.example.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Repo.UserEmployeeCombinedRepository;

import java.util.List;

@Service
@Transactional
public class UserEmployeeService {

    @Autowired
    private UserEmployeeCombinedRepository userEmployeeCombinedRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveDataFromSQLQuery() {

        List<UserEmployeeCombined> userEmployeeList = jdbcTemplate.query(
                "SELECT " +
                        "u.id AS id, " +
                        "u.suffix AS suffix, " +
                        "u.firstname AS firstname, " +
                        "u.middlename AS middlename, " +
                        "u.lastname AS lastname, " +
                        "u.gender AS gender, " +
                        "u.dob AS dob, " +
                        "u.role AS role, " +
                        "u.location AS location, " +
                        "u.number AS number, " +
                        "u.email AS email, " +
                        "u.address AS address, " +
                        "u.city AS city, " +
                        "u.state AS state, " +
                        "u.zip AS zip, " +
                        "u.comments AS comments, " +
                        "e.date_of_joining AS dateOfJoining, " +
                        "e.years_of_experience AS yearsOfExperience, " +
                        "e.relevant_certifications AS relevantCertifications, " +
                        "e.role AS employeeRole, " +
                        "e.location AS employeeLocation, " +
                        "e.salary AS salary, " +
                        "e.is_full_time AS isFullTime " +
                        "FROM user u " +
                        "JOIN employee_information e ON u.id = e.eid",
                (rs, rowNum) -> {
                    UserEmployeeCombined userEmployee = new UserEmployeeCombined();
                    userEmployee.setId(rs.getLong("id"));
                    userEmployee.setSuffix(rs.getString("suffix"));
                    userEmployee.setFirstname(rs.getString("firstname"));
                    userEmployee.setMiddlename(rs.getString("middlename"));
                    userEmployee.setLastname(rs.getString("lastname"));
                    userEmployee.setGender(rs.getString("gender"));
                    userEmployee.setDob(rs.getDate("dob").toLocalDate());
                    userEmployee.setRole(rs.getString("role"));
                    userEmployee.setLocation(rs.getString("location"));
                    userEmployee.setNumber(rs.getString("number"));
                    userEmployee.setEmail(rs.getString("email"));
                    userEmployee.setAddress(rs.getString("address"));
                    userEmployee.setCity(rs.getString("city"));
                    userEmployee.setState(rs.getString("state"));
                    userEmployee.setZip(rs.getString("zip"));
                    userEmployee.setComments(rs.getString("comments"));
                    userEmployee.setDateOfJoining(rs.getDate("dateOfJoining").toLocalDate());
                    userEmployee.setYearsOfExperience(rs.getString("yearsOfExperience"));
                    userEmployee.setRelevantCertifications(rs.getString("relevantCertifications"));
                    userEmployee.setEmployeeRole(rs.getString("employeeRole"));
                    userEmployee.setEmployeeLocation(rs.getString("employeeLocation"));
                    userEmployee.setSalary(rs.getDouble("salary"));
                    userEmployee.setIsFullTime(rs.getString("isFullTime"));
                    return userEmployee;
                }
        );

        userEmployeeCombinedRepository.saveAll(userEmployeeList);
    }
    public void deleteUserById(Long id) {
        userEmployeeCombinedRepository.deleteById(id);
    }
    public List<UserEmployeeCombined> getAllUserEmployeeData() {
        return userEmployeeCombinedRepository.findAll();
    }
}
