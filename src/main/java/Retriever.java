import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;



public class Retriever{
	
	
	private String appParentFolderTag = "application_directory_parent";
	private String emailTag = "email";
	private String passwordTag = "password";
	private String smtpHostTag = "smtp_host";
	private String portTag = "port";
	private String companyDomainTag = "company_domain";
	
	private String fileName = "asme_config.xml";
		
	

	public void writeConfig() {
		
		File configuration = new File(fileName);
		

			try {
				
				DocumentBuilderFactory foundry = DocumentBuilderFactory.newInstance();
				DocumentBuilder build = foundry.newDocumentBuilder();
		    
				Document doc = build.newDocument();
						    
				Element root = doc.createElement("configuration");
				
				Element catalina = doc.createElement(appParentFolderTag);
				Element email = doc.createElement(emailTag);
				Element password = doc.createElement(passwordTag);
				Element smtp_host = doc.createElement(smtpHostTag);
				Element port = doc.createElement(portTag);
				Element company_domain = doc.createElement(companyDomainTag);
				
	
							
				doc.appendChild(root);
				root.appendChild(company_domain);
				root.appendChild(catalina);
				root.appendChild(smtp_host);
				root.appendChild(email);
				root.appendChild(password);
				root.appendChild(port);
				
				
								
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource domSource = new DOMSource(doc);
				StreamResult streamResult = new StreamResult(configuration);
				transformer.transform(domSource, streamResult);
				
				
	
			
			} catch (ParserConfigurationException e) {
			
	    		System.out.println(getClass().getName() + " - Error");
	    		System.out.println(e.toString());
	    		e.printStackTrace();
			
			} catch (TransformerConfigurationException e) {
				
	    		System.out.println(getClass().getName() + " - Error");
	    		System.out.println(e.toString());
	    		e.printStackTrace();
				
			} catch (TransformerException e) {
				
	    		System.out.println(getClass().getName() + " - Error");
	    		System.out.println(e.toString());
	    		e.printStackTrace();
	    		
			}  // End of catch blocks
			
			
			readConfig();
		
	//  configure.setReadOnly();
	
	
	
	
	
	} // End of readConfig
	
	public void readConfig() {
		
		
		File configuration = new File(fileName);
		
	
				
			try {
				
				
				Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(configuration);
	
								
				Props.appParent =  doc.getElementsByTagName(appParentFolderTag).item(0).getTextContent();
				Props.email = doc.getElementsByTagName(emailTag).item(0).getTextContent();
				Props.password = doc.getElementsByTagName(passwordTag).item(0).getTextContent();
				Props.smtpPort = doc.getElementsByTagName(portTag).item(0).getTextContent();
				Props.smtpHost = doc.getElementsByTagName(smtpHostTag).item(0).getTextContent();
				Props.companyDomain = doc.getElementsByTagName(companyDomainTag).item(0).getTextContent();
				
				Props.root = Props.appParent + "/" + Props.projectName; // Use for testing on development computer
				
				
				System.out.println(Props.appParent + Props.email + Props.companyDomain);
			    	 
				 
			} catch (SAXException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (IOException e) {
				
				System.out.println("No Config");
				
				writeConfig();
				
				e.printStackTrace();
				
			} catch(ParserConfigurationException e) {
				
				e.printStackTrace();
				
			} // End of try/catch 	
			
			
		
			
	} // End of readConfig
	

	
	

} // End of Class
