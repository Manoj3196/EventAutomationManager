package com.api.helper;

import com.api.entity.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;

public class Helper {


    //check that file is of excel type or not
    public static boolean checkExcelFormat(MultipartFile file) {

        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }


    //convert excel to list of products

    public static List<Employee> convertExcelToListOfEmployee(InputStream is) {
        List<Employee> list = new ArrayList<>();

        try {


            XSSFWorkbook workbook = new XSSFWorkbook(is);

            XSSFSheet sheet = workbook.getSheetAt(0);

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

                Employee e = new Employee();

                while (cells.hasNext()) {
                    Cell cell = cells.next();

                    switch (cid) {
                        case 0:
                            e.setEmpId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            e.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            e.setNCSID(cell.getStringCellValue());
                            break;
                        case 3:
                            e.setNCSEmail(cell.getStringCellValue());
                            break;
                        case 4:
                        	e.setBirthDayAndMonth(cell.getDateCellValue());
                            break;
                     
                        default:
                            break;
                    } 
                    cid++;

                }

                list.add(e);


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
