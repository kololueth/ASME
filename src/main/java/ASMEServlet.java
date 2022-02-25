import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.json.JSONObject;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ASMEServlet extends HttpServlet {
	
	/**
	 * status - body_content_processing_error = Issue with converting HTTP request body content to a String 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void init() throws ServletException {	
	
		// Set App Variables    
	    new Retriever().readConfig();
	    		
	} // End of init
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
	    response.setHeader("Access-Control-Allow-Origin", "*");
	   
	    
	    PrintWriter writer = response.getWriter();
		
				
				writer.println("<!DOCTYPE html>"
						+ "<html>"
						+ "<head>"
						+ "<meta charset=\"UTF-8\">"
						+ "<title>ASME</title>"
						+ "</head>"
						+ "<body> SUCCESS <p>It didn't break!</p> "
						+ "</body>"
						+ "</html>");
						
						
				writer.close();
	    
	   
		
	} // End of doGet

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType("text/plain");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		// Initialize response JSONObject
		JSONObject responseJSON = new JSONObject();
	
		
		// Client should be sending a JSON String
		JSONObject requestJSON = new JSONObject(getBodyContent(request));
		
	
			if(requestJSON.has("status")) {  // Problem in the getBodyContent Method
				
						//if(requestJSON.get("status").equals("body_content_processing_error")) { } // End of if

			} else if(requestJSON .has("function")) { // If the JSONObject has a key named "function", the JSONObject was sent from the application
				
				
						if(requestJSON.get("function").equals("email")) { 
							
			
							String recipient = requestJSON.get("email").toString();    // Basic email format validation should be done by the requestor.  Assume valid email format but not a valid email address.
			
								System.out.println(recipient);
								System.out.println(" Dhee - pie - yan");
								System.out.println(recipient);
								
							EmailSender emailSender = new EmailSender(recipient);
							
							
									if(emailSender.sendMessage() == true) {
										
										responseJSON.put("email", recipient);
										responseJSON.put("status", "confirmed"); // notifies application how to respond to the response. 
										
									} else {
										
										responseJSON = emailSender.errorJSON;
											
									} // End of if else
						
							
					} // End of if email

				
			} else {  // If the JSONObject does not have a key named "function", the JSONObject was most likely not sent from the application.  Or there was an issue in packet transport. 
				
						responseJSON.put("status", "UD");  // Unconfirmed Direction
				
			} // End if if/elseif/else
			
		
			response.getWriter().println(responseJSON.toString());
			
			
			
	} // End of doPost

	
	private String getBodyContent(HttpServletRequest request) {


            try {

                InputStreamReader characters = new InputStreamReader(request.getInputStream(), "UTF-8");

                BufferedReader bufferedReader= new BufferedReader(characters);

                String bodyContent = "";
                
                String string;
                
                while((string = bufferedReader.readLine()) != null) {
                	
                	
                	bodyContent = bodyContent + string; 
                	
                }  // End of while

                characters.close();
                bufferedReader.close();
                request.getInputStream().close();

                return bodyContent;


            } catch (IOException e) {
            	
            	return new JSONObject().put("status", "body_content_processing_error").toString();
            	
            } // End of try/catch



    } // End of getBodyContent
	
	
	public void destroy() {
		
	
		
	} // End of Destroy

	
	

} // End of ASMEServlet
