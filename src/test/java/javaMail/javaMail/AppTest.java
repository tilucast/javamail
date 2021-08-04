package javaMail.javaMail;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {
	
	
	
	@Test
	public void testEmail() {
		
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("<div style=\"width: 100%; height: 100%; background-color: red;\"><h2>Hi Hello There</h2></div>");
		strBuilder.append("<img src=\"https://static.wikia.nocookie.net/jjba/images/b/bf/Gyro.jpg/revision/latest/top-crop/width/360/height/450?cb=20150613223034\"></img>");
		
		try {
			
			SendJavaMail sendEmail = new SendJavaMail("benisqueniano@gmail.com", "Benis-123");
			
			sendEmail.setFrom("Benis Queniano - Shitty Company");
			sendEmail.setListOfDestinataries("asbiredebob@gmail.com");
			sendEmail.setSubject("Some shitty email that noone reads");
			sendEmail.setBodyMessage(strBuilder.toString());
			
			sendEmail.SendEmail();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    
}
