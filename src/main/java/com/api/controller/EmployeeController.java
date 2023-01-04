package com.api.controller;

import com.api.entity.Employee;
import com.api.helper.Helper;
import com.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true

            this.employeeService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping
    public List<Employee> getAllEmployee() {
        return this.employeeService.getAllEmployees();
    }
    
    
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee ) {
		
    	return new ResponseEntity<Employee>(employeeService.addEmployee(employee), HttpStatus.OK);
	}

}
