package com.easypick.admin.defaults.controller;
import javax.mail.internet.MimeMessage; 

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service; 
 
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper; 
@Service("mailService")
public class ApplicationMailer
{
	@Autowired
	private JavaMailSender mailSenderObj;

//	public boolean sendEmail(EmailClient client) 
//	{
//
//		try
//		{
//
//			MimeMessage mimeMessage = mailSenderObj.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
//			String htmlMsg = "<h3>Hello World!</h3>";
//			mimeMessage.setContent(htmlMsg, "text/html");
//			helper.setTo("vineeth.b20@gmail.com");
//			helper.setSubject("This is the test message for testing gmail smtp server using spring mail");
//			helper.setFrom("picmass.com@gmail.com");
//			mailSenderObj.send(mimeMessage);
//			return true;
//		}
//		catch(Exception e)
//		{
//			return false;
//		}
//
//	}


}