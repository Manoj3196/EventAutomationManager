package com.api.service;

import com.api.entity.Employee;
import com.api.helper.Helper;
import com.api.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public void save(MultipartFile file) {

        try {
            List<Employee> employees = Helper.convertExcelToListOfEmployee(file.getInputStream());
            this.employeeRepo.saveAll(employees);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Employee> getAllEmployees() {
        return this.employeeRepo.findAll();
        
    }
}
