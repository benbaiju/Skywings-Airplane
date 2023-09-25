package com.example.demo;
import com.example.demo.Controller.UserController;
import com.example.demo.Models.EmployeeInformation;
import com.example.demo.Models.User;
import com.example.demo.Models.UserEmployeeCombined;
import com.example.demo.Repo.EmployeeInformationRepo;
import com.example.demo.Repo.UserEmployeeCombinedRepository;
import com.example.demo.Repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserRepo userrepo;

	@Autowired
	EmployeeInformationRepo employeerepo;

	@Autowired
	UserEmployeeCombinedRepository useremployeerepo;


	@Test
	void contextLoads() {
	}

	@Test
	public void testcreate()
	{

		User user1 = new User();
		//user1.setId(1L);
		user1.setSuffix("Mr.");
		user1.setFirstname("John");
		user1.setMiddlename("Middle");
		user1.setLastname("Doe");
		user1.setGender("Male");
		user1.setDob(LocalDate.of(1990, 1, 15));
		user1.setRole("User");
		user1.setLocation("City");
		user1.setNumber("1234567890");
		user1.setEmail("john.doe@example.com");
		user1.setAddress("123 Main St");
		user1.setCity("Springfield");
		user1.setState("IL");
		user1.setZip("123456");
		user1.setComments("This is a comment");
		userrepo.save(user1);
		assertNotNull(userrepo.findById(10L).get());

	}

	@Test
	public void testReadall()
	{
		List<User> list=userrepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	}

	@Test
	public void testUpdate(){
		User user=userrepo.findById(3L).get();
	}

	@Test
	public void testDelete(){
		userrepo.deleteById(5L);
		assertThat(userrepo.existsById(5L)).isFalse();

	}
	@Test
	public void testCreateEmployeeInformation() {
		EmployeeInformation employeeInfoToCreate = new EmployeeInformation();
		employeeInfoToCreate.setDateOfJoining(LocalDate.of(2022, 9, 1));
		employeeInfoToCreate.setYearsOfExperience("3 years");
		employeeInfoToCreate.setRelevantCertifications("Cert1, Cert2");
		employeeInfoToCreate.setRole("Software Engineer");
		employeeInfoToCreate.setLocation("New York");
		employeeInfoToCreate.setSalary(75000.0);
		employeeInfoToCreate.setIsFullTime("Yes");

		EmployeeInformation createdEmployeeInfo = employeerepo.save(employeeInfoToCreate);
		assertNotNull(employeerepo.findById(10L).get());



	}
	@Test
	public void testReadallEmployee()
	{
		List<EmployeeInformation> list=employeerepo.findAll();
		assertThat(list).size().isGreaterThan(0);

	}

	@Test
	public void testUpdateEmployee(){
		EmployeeInformation employeeInformation=employeerepo.findById(3L).get();
	}
	@Test
	public void testDeleteEmployee(){
		employeerepo.deleteById(5L);
		assertThat(employeerepo.existsById(5L)).isFalse();

	}

	@Test
	public void testCreateUserEmployeeCombined() {
		UserEmployeeCombined userEmployeeCombinedToCreate = new UserEmployeeCombined();
		userEmployeeCombinedToCreate.setSuffix("Mr.");
		userEmployeeCombinedToCreate.setFirstname("John");
		userEmployeeCombinedToCreate.setMiddlename("Middle");
		userEmployeeCombinedToCreate.setLastname("Doe");
		userEmployeeCombinedToCreate.setGender("Male");
		userEmployeeCombinedToCreate.setDob(LocalDate.of(1990, 1, 15));
		userEmployeeCombinedToCreate.setRole("User");
		userEmployeeCombinedToCreate.setLocation("City");
		userEmployeeCombinedToCreate.setNumber("1234567890");
		userEmployeeCombinedToCreate.setEmail("john.doe@example.com");
		userEmployeeCombinedToCreate.setAddress("123 Main St");
		userEmployeeCombinedToCreate.setCity("Springfield");
		userEmployeeCombinedToCreate.setState("IL");
		userEmployeeCombinedToCreate.setZip("123456");
		userEmployeeCombinedToCreate.setComments("This is a comment");

		userEmployeeCombinedToCreate.setDateOfJoining(LocalDate.of(2022, 9, 1));
		userEmployeeCombinedToCreate.setYearsOfExperience("3 years");
		userEmployeeCombinedToCreate.setRelevantCertifications("Cert1, Cert2");
		userEmployeeCombinedToCreate.setEmployeeRole("Software Engineer");
		userEmployeeCombinedToCreate.setEmployeeLocation("New York");
		userEmployeeCombinedToCreate.setSalary(75000.0);
		userEmployeeCombinedToCreate.setIsFullTime("Yes");

		useremployeerepo.save(userEmployeeCombinedToCreate);
		assertNotNull(useremployeerepo.findById(10L).get());



	}

	@Test
	public void testReadallUserEmployee()
	{
		List<UserEmployeeCombined> list=useremployeerepo.findAll();
		assertThat(list).size().isGreaterThan(0);

	}

	@Test
	public void testUpdateUserEmployee(){
		UserEmployeeCombined employeeInformation=useremployeerepo.findById(3L).get();
	}
	@Test
	public void testDeleteUserEmployee(){
		useremployeerepo.deleteById(3L);
		assertThat(useremployeerepo.existsById(5L)).isFalse();

	}


}
