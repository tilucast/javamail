package javaMail.javaMail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class SendJavaMail {
	
	private String email = "";
	private String password = "";
	
	private String listOfDestinataries = "";
	private String from = "";
	private String subject = "";
	private String bodyMessage = "";
	
	public SendJavaMail(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public void SendEmail() throws Exception{
			
		Properties props = new Properties();
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(email, password);
			}
		});
		
		Address[] users = InternetAddress.parse(listOfDestinataries);
		
		MimeBodyPart emailBody = new MimeBodyPart();
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email, from));
		message.setRecipients(Message.RecipientType.TO, users);
		message.setSubject(subject);
		emailBody.setContent(bodyMessage, "text/html; charset=utf-8");
		
		//message.setContent(bodyMessage, "text/html; charset=utf-8");
		//message.setText(bodyMessage);
		
		
		
		// The code below inserts an attachment to an email.
		
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(emailBody);
		
			// Simulating multiple attachments
			
			List<FileInputStream> files = new ArrayList<FileInputStream>();
			files.add(pdf());
			files.add(pdf());
			files.add(pdf());
			files.add(pdf());
			
			int i = 0;
			
			
			for (FileInputStream fileInputStream : files) {
				MimeBodyPart attachment = new MimeBodyPart();
				attachment.setDataHandler(new DataHandler(new ByteArrayDataSource(fileInputStream, "application/pdf")));
				attachment.setFileName("anexoemail"+i+".pdf");
				
				multipart.addBodyPart(attachment);
				i++;
			}
			
			// end of code block
		
		
		message.setContent(multipart);
		
		
		// end of the code block
		
		
		Transport.send(message);
		System.out.println("Email sent successfully.");
			
	}
	
	private FileInputStream pdf() throws Exception{
		// Simulate an pdf file
		
		Document document = new Document();
		File file = new File("file.pdf");
		//file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Some shitty text here generated with java"));
		document.close();
		
		return new FileInputStream(file);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getListOfDestinataries() {
		return listOfDestinataries;
	}

	public void setListOfDestinataries(String listOfDestinataries) {
		this.listOfDestinataries = listOfDestinataries;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyMessage() {
		return bodyMessage;
	}

	public void setBodyMessage(String bodyMessage) {
		this.bodyMessage = bodyMessage;
	}
	
	

}
