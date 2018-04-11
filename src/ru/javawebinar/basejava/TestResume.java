package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.io.File;
import java.time.Month;

public class TestResume {
    public static void main(String[] args) {

        Resume res = new Resume("Ivanov Ivan");
        res.setContacts(ContactType.PHONE, "+7(921) 855-0482");
        res.setContacts(ContactType.SKYPE, "grigory.kislin");
        res.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        res.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        res.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        res.setContacts(ContactType.HOMEPAGE, "http://gkislin.ru/");

        System.out.println(res.getFullName());
        res.getContacts();

        StringField objective = new StringField("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        res.setSections(SectionType.OBJECTIVE, objective);

        ListField<String> personal = new ListField<>();
        personal.add("Аналитический склад ума, сильная логика, креативность, инициативность.");
        personal.add("Пурист кода и архитектуры.");
        res.setSections(SectionType.PERSONAL, personal);

        ListField<String> achievment = new ListField<>();
        achievment.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.\n");
        achievment.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievment.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievment.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievment.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");

        res.setSections(SectionType.ACHIEVEMENT, achievment);

        ListField<String> qualifications = new ListField<>();
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
        res.setSections(SectionType.QUALIFICATIONS, qualifications);

        ListField<Experience> exp = new ListField<>();
        Experience exp1 = new Experience("Java Online Projects","");
        exp1.addPeriod(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016,Month.JANUARY), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");

        Experience exp2 = new Experience("Wrike","");
        exp2.addPeriod(DateUtil.of(2014, Month.OCTOBER), DateUtil.of(2016,Month.JANUARY), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");

        exp.add(exp1);
        exp.add(exp2);
        res.setSections(SectionType.EXPERIENCE, exp);

        ListField<Experience> edu = new ListField<>();
        Experience edu1 = new Experience("Coursera","coursera.com");
        edu1.addPeriod( DateUtil.of(2013, Month.MARCH), DateUtil.of(2013, Month.MAY), "\"Functional Programming Principles in Scala\" by Martin Odersky", null);
        edu.add(edu1);

        Experience edu2 = new Experience("Luxoft","luxoft.ru");
        edu2.addPeriod(DateUtil.of(2011, Month.MARCH),DateUtil.of(2011, Month.APRIL) , "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null);
        edu.add(edu2);

        res.setSections(SectionType.EDUCATION, edu);

        res.getAllSection();
        FileStorage storage = new FileStorage(new File(".//ResumeStorage"));
        storage.clear();
        storage.save(res);
        storage.clear();
        //storage.delete("d25e8069-40e6-4906-8724-f26a55b61e66");
       // storage.update(res);
    }

}