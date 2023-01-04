package com.api.repo;

import com.api.entity.Employee;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	
    List<Employee>  findByDateOfBirth(Date dateOfBirth);
    
    List<Employee> findByDateOfMarriage(Date dateOfMarriage);

    List<Employee> findByEmailId(String emailId);
    
    

   @Query(value = "select e  from Employee e")
    public List<Employee> getDateOfBirth();

    @Query(value = "select e  from Employee e")
    public List<Employee> getJoiningDate();

    @Query(value = "select e  from Employee e")
    public List<Employee> getEmailId();

}
