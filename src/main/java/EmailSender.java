import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONObject;

public class EmailSender {
	
	
	Session session;

	String sender;
	String recipient;
	String path_LifeGuide;
	
	JSONObject errorJSON;
	
	boolean propertiesSet;
	
	

	public EmailSender(String recipient) {
		
		// Error Bank
		
		this.recipient = recipient;
		
		errorJSON = new JSONObject();
		
		propertiesSet = checkProperties();
		
		
		if(propertiesSet) {
			
			/////////////////////////////////////////////////
			// Paths for attachments
			/////////////////////////////////////////////////
			path_LifeGuide = Props.root + "/src/main/webapp/WEB-INF/98401 ASME Life Decision Download.pdf";
			
			// Mail Session
			this.session = createSession();
			session.setDebug(true);
			
			
		} else {
			
			errorJSON.put("email", recipient);
			errorJSON.put("status", "error"); // notifies application how to respond to the response.  
			errorJSON.put("status_message", "Properties not set");
			
		}


		
	} // End of Constructor
	
	
	private boolean checkProperties() {
		

		boolean set = true;
		
		set = !Props.appParent.contains("ENTER"); System.out.println(set);
		set = !Props.email.contains("ENTER"); System.out.println(set);
		set = !Props.password.contains("ENTER"); System.out.println(set);
		set = !Props.smtpHost.contains("ENTER"); System.out.println(set);
		set = !Props.smtpPort.contains("ENTER"); System.out.println(set);
		
		System.out.println("Check configuration file here " + new File(Props.configPath).getAbsolutePath());

		
		return set;
			
	}
	
	
	private Session createSession() {
		
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.host", Props.smtpHost);
		properties.put("mail.smtp.port", Props.smtpPort);

		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.ssl.enable", true);
		//properties.put("mail.smtp.starttls.enable", true);
		
		
		Authenticator authenticator = new Authenticator() {
			
			@Override
			public javax.mail.PasswordAuthentication getPasswordAuthentication() {
				
				return new javax.mail.PasswordAuthentication(Props.email, Props.password);
				
			}
			
		};
		
		
		return Session.getInstance(properties, authenticator);
		
		
	} // End of setSession
	
	
	public boolean sendMessage() {
		
		if(propertiesSet) {
		
		
			MimeMessage mMessage = new MimeMessage(session);
			
			
				try {
					
				
					buildMessage(mMessage);
					
					Transport.send(mMessage);
					
					return true;
					
					
				} catch (MessagingException e) {
				
					errorJSON.put("email", recipient);
					errorJSON.put("status", "error"); // notifies application how to respond to the response.  
					errorJSON.put("status_message", e.toString());
				
					return false;
					
				} // End of try/catch
				
			
		} // End of if
			
			
			return false;
		
		
		
	} // End of sendPost
	
	
	private void buildMessage(MimeMessage mMessage) throws MessagingException{
		
		
		DataSource source = new FileDataSource(path_LifeGuide);
			
		
			// Message Headers
			mMessage.setFrom(Props.email);
			mMessage.setRecipients(Message.RecipientType.TO, recipient);
			mMessage.setSubject("Document - ASME Decision Life Guide");
			mMessage.setSentDate(new Date());
			
			
			//Message Body
			Multipart mp = new MimeMultipart();
			BodyPart body = new MimeBodyPart();
			BodyPart attachment = new MimeBodyPart();
		
			body.setText("Future Customer, \n\n\r The ASME Decision Life Guide has been sent to you!  \n\n\r ASME Insurance");
			
			
			// Attachments
			attachment.setDataHandler(new DataHandler(source));
			attachment.setFileName("ASME Life Decision Guide");
			
			
			// construct complete message
			mp.addBodyPart(body);
			mp.addBodyPart(attachment);	
			
			
			mMessage.setContent(mp);
				

	}  // End of setMessage
	

	
} // End of EmailSender
