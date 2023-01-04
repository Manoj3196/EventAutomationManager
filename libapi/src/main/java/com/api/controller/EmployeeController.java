package com.api.controller;

import com.api.entity.Employee;
import java.text.SimpleDateFormat;

import com.api.helper.Helper;
import com.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
//    private List<BirthDayConfig> config = new ArrayList<>();
    
  

    @PostMapping("/employee/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Helper.checkExcelFormat(file)) {
            //true

            this.employeeService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }


    @GetMapping("/employee")
    public List<Employee> getEmployee() {
    	List<Employee> employeeList = employeeService.getAllEmployees();
		return employeeList;
		
    
		LocalDate localDate = LocalDate.now();
	
	
		
		
		
		
//		for(Employee employee : employeeList)
//		{
//			try
//			{
//				if (isItBirthday(employee))
//				{
//					birthDayToday.add(employee);
//				}
//			}
//			catch (Exception e)
//			{
//				logger.error("Error getting  date - "+" EXCEPTION : "+ex.getCode()+" : "+ex.getMessage());
//			}
//		}
    }
    
//	private boolean isItBirthday(Employee employee)
//	{
//		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
//		Date today = new Date();
//		Date birthDay = employee.getBirthday();
//		if(birthDay == null)
//		{
//			throw new Exception("Birth date is null", "201");
//		}
//		return (formatter.format(today).equals(formatter.format(birthDay)));
//	}
	
   

}
