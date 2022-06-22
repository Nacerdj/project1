package com.app.jdbc;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	
	public boolean sendEmail(String toAddress, String status, int rId){
		 boolean result = false;
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		 // Get a Properties object
		    Properties props = System.getProperties();
		    props.setProperty("mail.smtp.host", "smtp.gmail.com");
		    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		    props.setProperty("mail.smtp.socketFactory.fallback", "false");
		    props.setProperty("mail.smtp.port", "465");
		    props.setProperty("mail.smtp.socketFactory.port", "465");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.debug", "true");
		    props.put("mail.store.protocol", "pop3");
		    props.put("mail.transport.protocol", "smtp");
		    final String username = "djabournacer1@gmail.com"; // sender gmail id
		    final String password = "Winter@2022";
		    try{
		      Session session = Session.getDefaultInstance(props, 
		                          new Authenticator(){
		                             protected PasswordAuthentication getPasswordAuthentication() {
		                                return new PasswordAuthentication(username, password);
		                             }});

		   // -- Create a new message --
		      Message msg = new MimeMessage(session);

		   // -- Set the FROM and TO fields --
		      msg.setFrom(new InternetAddress(username));
		      msg.setRecipients(Message.RecipientType.TO, 
		                        InternetAddress.parse(toAddress,false));
		      msg.setSubject("Reimbursement Resolved for id : "+rId);
		      msg.setText("Status of your Reimbursement Id : "+rId+" is "+status);
		      msg.setSentDate(new Date());
		      Transport.send(msg);
		      result = true;
		      System.out.println("send email success");
		    }catch (MessagingException e){ 
		      System.out.println("Erreur d'envoi, cause: " + e);
		    }
		return result;

	}

}
