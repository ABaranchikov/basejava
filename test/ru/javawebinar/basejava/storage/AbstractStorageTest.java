package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {

    protected static final String STORAGE_DIR = Config.getInstance().getStorageDir();

    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    private static final String NAME_1 = "Grigory Kislin";
    private static final String NAME_2 = "Jhon Doe";
    private static final String NAME_3 = "Tom Sawyer";
    private static final String NAME_4 = "Gek Finn";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1, NAME_1);
        RESUME_2 = new Resume(UUID_2, NAME_2);
        RESUME_3 = new Resume(UUID_3, NAME_3);
        RESUME_4 = new Resume(UUID_4, NAME_4);

        RESUME_4.addContact(ContactType.PHONE, "44444");
        RESUME_4.addContact(ContactType.SKYPE, "Skype");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    public void fillResume(Resume res) {
        res.addContact(ContactType.PHONE, "+7(921) 855-0482");
        res.addContact(ContactType.SKYPE, "grigory.kislin");
        res.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        res.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        res.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        res.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        StringField objective = new StringField("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        res.addSection(SectionType.OBJECTIVE, objective);

        StringField personal = new StringField("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        res.addSection(SectionType.PERSONAL, personal);

        List<String> achievment = new ArrayList<>();
        achievment.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievment.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        achievment.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        achievment.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        achievment.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");

        res.addSection(SectionType.ACHIEVEMENT, new ListField(achievment));

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
        res.addSection(SectionType.QUALIFICATIONS, new ListField(qualifications));

       /* Experience.Periods period1 = new Experience.Periods(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Experience exp1 = new Experience("Java Online Projects", "", period1);

        Experience.Periods period2 = new Experience.Periods(2014, Month.OCTOBER, 2016, Month.JANUARY, "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        Experience exp2 = new Experience("Wrike", "", period2);

        res.addSection(SectionType.EXPERIENCE, new ExperienceField(exp1, exp2));

        Experience edu1 = new Experience("Coursera", "coursera.com",
                new Experience.Periods(2013, Month.MARCH, 2013, Month.MAY, "\"Functional Programming Principles in Scala\" by Martin Odersky", null));

        Experience edu2 = new Experience("Luxoft", "luxoft.com",
                new Experience.Periods(2011, Month.MARCH, 2011, Month.APRIL, "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"", null));

        Experience edu3 = new Experience("Siemens AG", "siemens.com",
                new Experience.Periods(2005, Month.JANUARY, 2005, Month.APRIL, "3 месяца обучения мобильным IN сетям (Берлин)", null));

        Experience edu4 = new Experience("Alcatel", "alcatel.com",
                new Experience.Periods(1997, Month.SEPTEMBER, 2008, Month.MARCH, "6 месяцев обучения цифровым телефонным сетям (Москва)", null));

        Experience edu5 = new Experience("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "www.ifmo.ru",
                new Experience.Periods(1993, Month.SEPTEMBER, 1996, Month.JULY, "Аспирантура (программист С, С++)", null),
                new Experience.Periods(1987, Month.SEPTEMBER, 1993, Month.JULY, "Инженер (программист Fortran, C)", null));
        ExperienceField edu = new ExperienceField(edu1, edu2, edu3, edu4, edu5);

        res.addSection(SectionType.EDUCATION, edu);
*/
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        fillResume(RESUME_1);
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void update() throws Exception {
        Resume newResume = new Resume(UUID_1, "New name");
      //  fillResume(newResume);
        newResume.addContact(ContactType.MAIL, "mail1@google.com");
        newResume.addContact(ContactType.SKYPE, "NewSkype");
        newResume.addContact(ContactType.PHONE, "+7 921 222-22-22");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> list = storage.getAllSorted();
        assertEquals(3, list.size());
        assertEquals(RESUME_1, list.get(0));
        assertEquals(RESUME_2, list.get(1));
        assertEquals(RESUME_3, list.get(2));
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}
