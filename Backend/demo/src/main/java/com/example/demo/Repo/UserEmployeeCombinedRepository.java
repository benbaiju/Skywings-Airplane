package com.example.demo.Repo;
import com.example.demo.Models.UserEmployeeCombined;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmployeeCombinedRepository extends JpaRepository<UserEmployeeCombined, Long> {
}


