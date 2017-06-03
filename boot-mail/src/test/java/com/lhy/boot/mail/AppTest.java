package com.lhy.boot.mail;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)  
public class AppTest{
	
	@Autowired
	private BootMail bootMail;
	
	@Test
	public void testSendMessage() throws InterruptedException{
		bootMail.sendMessage();
	}
	
	@Test
	public void testSendMimeMessage() throws InterruptedException{
		bootMail.sendMimeMessage();
	}
	
	@Test
	public void testSendInlineMail() throws InterruptedException{
		bootMail.sendInlineMail();
	}
	
	@Test
	public void testSendTemplateMail() throws InterruptedException{
		bootMail.sendTemplateMail();
	}
	
	
	
	
	
}
