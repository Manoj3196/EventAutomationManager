package com.api.helper;

import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.api.entity.Employee;

public class Helper {

	// check that file is of excel type or not
	public static boolean checkExcelFormat(MultipartFile file) {

		String contentType = file.getContentType();

		if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return true;
		} else {
			return false;
		}

	}

	// convert excel to list of products

	public static List<Employee> convertExcelToListOfProduct(InputStream is) {
		List<Employee> list = new ArrayList<>();

		try {

			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
//            XSSFSheet sheet = workbook.getSheet("data");

			int rowNumber = 0;
			Iterator<Row> iterator = sheet.iterator();

			while (iterator.hasNext()) {
				Row row = iterator.next();

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cells = row.iterator();

				int cid = 0;

				Employee employee = new Employee();

				while (cells.hasNext()) {
					Cell cell = cells.next();

					switch (cid) {
					case 0:

						employee.setEmpId((long) cell.getNumericCellValue());
						break;
					case 1:
						employee.setEmployeeName(cell.getStringCellValue());
						break;

					case 2:
						employee.setContactNo((long) cell.getNumericCellValue());
						break;
					case 3:
						employee.setEmailId(cell.getStringCellValue());
						break;

					case 4:
						employee.setOnBoardingDate(cell.getDateCellValue());
						break;
					case 5:
						employee.setDateOfBirth(cell.getDateCellValue());
						break;

					case 6:
						employee.setDateOfMarriage(cell.getDateCellValue());
						break;

					case 7:
						employee.setManagerName(cell.getStringCellValue());
						break;

					case 8:
						employee.setEmpStatus(cell.getStringCellValue());
						break;

					default:
						break;
					}
					cid++;

				}

				list.add(employee);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

}
