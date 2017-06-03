package com.lhy.boot.mail;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class ThymeleafUtils {
	
	@Autowired
	private TemplateEngine templateEngine;

	public String convertMapByTemplate(String templateFileName,
			Map<String, Object> data) {
		Locale locale = Locale.getDefault();
		//templateEngine.setTemplateResolver(servletContextTemplateResolver);
		return templateEngine.process(templateFileName, new Context(locale,
				data));

	}
}
