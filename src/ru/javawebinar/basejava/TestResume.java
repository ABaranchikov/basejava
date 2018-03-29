package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;

import java.util.*;
public class TestResume{
	public static void main(String[] args){

		Resume res = new Resume("Ivanov Ivan");
		res.setContacts("phone", "+7(921) 855-0482");
		res.setContacts("skype", "grigory.kislin");
		res.setContacts("LinkedIn", "https://www.linkedin.com/in/gkislin");
		res.setContacts("GitHub", "https://github.com/gkislin");
		res.setContacts("stackoverflow", "https://stackoverflow.com/users/548473/gkislin");
		res.setContacts("homepage", "http://gkislin.ru/");

		System.out.println(res.getFullName());
		res.getContacts();

		List<String> objective = new ArrayList<>();
		objective.add("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
		res.setTextFields("OBJECTIVE",objective);

		List<String> personal = new ArrayList<>();
		personal.add("Аналитический склад ума, сильная логика, креативность, инициативность.");
		personal.add("Пурист кода и архитектуры.");
		res.setTextFields("PERSONAL",personal);

		List<String> achievment = new ArrayList<>();
		achievment.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievment.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievment.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievment.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievment.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");

        res.setTextFields("ACHIEVEMENT",achievment);

		List<String> qualifications = new ArrayList<>();
		qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2"); 
		qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce"); 
		qualifications.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle");
		qualifications.add("MySQL, SQLite, MS SQL, HSQLDB"); 
		qualifications.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy"); 
		qualifications.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts"); 
		qualifications.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."); 
		qualifications.add("Python: Django"); 
		qualifications.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
		qualifications.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
		qualifications.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT");
		qualifications.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
		res.setTextFields("QUALIFICATIONS",qualifications);

		List<Resume.Experience> exp = new ArrayList<>();
		exp.add(new Resume.Experience("Java Online Projects","10/2013","now","Автор проекта.","Создание, организация и проведение Java онлайн проектов и стажировок."));
		exp.add(new Resume.Experience("Wrike","10/2014","01/2016","Старший разработчик (backend)","Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
		res.setTextFields("EXPERIENCE",exp);

		List<Resume.Experience> edu = new ArrayList<>();
		edu.add(new Resume.Experience("Coursera","03/2013","05/2013","\"Functional Programming Principles in Scala\" by Martin Odersky",""));
		edu.add(new Resume.Experience("Luxoft","03/2011","04/2011","Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",""));
		res.setTextFields("EDUCATION",edu);

		for (SectionType type : SectionType.values()){
			System.out.println(type.getTitle()+": ");
			res.getTextFields(type.name());
		}
	}

}