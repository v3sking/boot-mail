package com.lhy.boot.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * Hello world!
 * 
 */
@Component
public class BootMail {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	@Autowired
	private ThymeleafUtils thymeleafUtils;
	
	public void sendMessage(){
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from+"@sina.com");
		simpleMailMessage.setTo("250985725@qq.com");
		simpleMailMessage.setSubject("关于自助服务jdk升级以及服务器调整为tomcat的说明");
		simpleMailMessage.setText("关于自助服务jdk升级以及服务器调整为tomcat的说明,后续平台组需要进行自动化部署, 通过spring cloud config云端配置文件下发,需要jdk1.7或以上支持,但是自助服务目前jdk版本1.6后续将无法对接平台,因此考虑升级jdk.Jdk是向下兼容的,可以无缝向上升级.测试环境已验证");
		mailSender.send(simpleMailMessage);
		System.out.println("send ok");
	}
	
	public void sendMimeMessage(){
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(mimeMessage,true);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			helper.setFrom(from+"@sina.com");
			helper.setTo("250985725@qq.com");
			helper.setSubject("主题：有附件");
			helper.setText("有附件的邮件");
			
			File file2 = new File("weixin.png");
			System.out.println(file2.getAbsolutePath());
			System.out.println("send ok");
			if (file2.exists()) {
				FileSystemResource file = new FileSystemResource(file2);
				helper.addAttachment("附件-1.jpg", file);
				helper.addAttachment("附件-2.jpg", file);
				mailSender.send(mimeMessage);
				System.out.println("send ok");
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public void sendInlineMail(){
		try {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(from+"@sina.com");
		helper.setTo("250985725@qq.com");
		helper.setSubject("主题：嵌入静态资源");
		helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);
		FileSystemResource file = new FileSystemResource(new File("weixin.png"));
		helper.addInline("weixin", file);
		mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendTemplateMail(){
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
			helper.setFrom(from+"@sina.com");
			helper.setTo("250985725@qq.com");
			helper.setSubject("主题：嵌入静态资源");
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("username", "张三丰");
			String text= thymeleafUtils.convertMapByTemplate("index", data);
			helper.setText(text, true);
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void testTemplate(){
		try {
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("username", "张三丰");
			String text= thymeleafUtils.convertMapByTemplate("index", data);
			logger.info(text);
			String text1= thymeleafUtils.convertMapByTemplate("index1", data);
			logger.info(text1);
			String text2= thymeleafUtils.convertMapByTemplate("index1", data);
			logger.info(text2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
