package com.api.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.entity.Employee;
import com.api.repo.EmployeeRepo;

@Service
public class BirthdayService {

	@Autowired
	public JavaMailSender mailSender;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Value("${spring.mail.username}")
	private String fromEmailID;

	@Value("${spring.mail.password}")
	private String password;

	@Value("${spring.mail.host}")
	private String host;

	public String sendBirthdayEmail() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmailID, password);
			}
		});

		try {

			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new Date(date.getTime());
			List<Employee> employeeList =employeeRepo.findByDateOfBirth(sqlDate);

			List<Employee> birthDate_list = employeeRepo.getDateOfBirth();

			System.out.println(birthDate_list);

			for (Employee employee : birthDate_list) {
				java.sql.Date bDate = (Date) employee.getDateOfBirth();

				System.out.println(bDate);

				String tempDate = String.valueOf(employee.getDateOfBirth());

				String tempDate2 = String.valueOf(sqlDate);

				int fromDay1 = LocalDate.parse(tempDate).getDayOfMonth();
				int monthValue1 = LocalDate.parse(tempDate).getMonthValue();

				int fromDay2 = LocalDate.parse(tempDate2).getDayOfMonth();
				int monthValue2 = LocalDate.parse(tempDate2).getMonthValue();

				if (fromDay1 == fromDay2 && monthValue1 == monthValue2) {
					MimeMessage message = new MimeMessage(session);
					message.setFrom(new InternetAddress(fromEmailID));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(employee.getEmailId()));
					message.setSubject("Birthday wishes..");

//					message.setContent("index.html", "text/html");

//					message.setContent("<h1>Happy Birthday..</h1>", "text/html ");
					message.setContent("<!DOCTYPE html>\n" + "<html>\n" + "<head>\n"
							+ " <title>Happy Birthday</title>\n" + " <meta charset=\"utf-8\" />\n" + "</head>\n"
							+ "<body style=\"color:#FA7070;font-size:25px;text-align:center;\">\n"
							+ " <div id=\"greetingCard\">\n" + "  <h1>Happy Birthday, <span class=\"redText\">"
							+ employee.getEmployeeName() + "</span>!</h3>\n"
							+ "  <p>We sincerely wish you <strong>success</strong> and <strong>happiness</strong> in your future life.</p>\n"
							+ "  <p>You're a great person!</p>\n"
							+ "  <h1 style=\"color:#6F38C5;font-size:30rem; class=\"signature\">Ncs...!</h1>\n"
							+ " </div>\n" + "</body>\n" + "</html>", "text/html ");

//                    message.setContent("<!DOCTYPE html>\r\n"
//                    		+ "<html lang=\"en\">\r\n"
//                    		+ "\r\n"
//                    		+ "<head>\r\n"
//                    		+ "    <meta charset=\"UTF-8\">\r\n"
//                    		+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
//                    		+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
//                    		+ "    <link rel=\"stylesheet\" href=\"./index.css\">\r\n"
//                    		+ "    <title>Document</title>\r\n"
//                    		+ "</head>\r\n"
//                    		+ "\r\n"
//                    		+ "<body>\r\n"
//                    		+ "\r\n"
//                    		+ "    <div class=\"main\">\r\n"
//                    		+ "        <div class=\"heading\">\r\n"
//                    		+ "            <div class=\"container1\">\r\n"
//                    		+ "\r\n"
//                    		+ "\r\n"
//                    		+ "                <img src=\"./img/b_image.gif\" class=\"birthday_image\" alt=\"image-error\">\r\n"
//                    		+ "\r\n"
//                    		+ "                <div class=\"sub_heading\">\r\n"
//                    		+ "                    <h1 class=\"animate-charcter\">Happy Birthday !</h1>\r\n"
//                    		+ "                    <h3>to "+employeeMaster.getEmployeeName()+"</h3> \r\n"
//                    		+ "                    <p><i>Wish you happy birthday from <b style=\"color: brown;font-size: 23px; font-weight: 900;\">NCS Family</b></i></p>\r\n"
//                    		+ "                </div>\r\n"
//                    		+ "                </div>\r\n"
//                    		+ "                \r\n"
//                    		+ "            </div>\r\n"
//                    		+ "           \r\n"
//                    		+ "       \r\n"
//                    		+ "    </div>\r\n"
//                    		+ "</body>\r\n"
//                    		+ "\r\n"
//                    		+ "</html>","text/html");

					mailSender.send(message);

					System.out.println("Sent message successfully....");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return "Error while Sending Mail";
	}

	public List<Employee> getBirthDate() {
		return employeeRepo.getDateOfBirth();
	}
}
