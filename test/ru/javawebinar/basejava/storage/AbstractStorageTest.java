package ru.javawebinar.basejava.storage;

import org.junit.*;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.DateUtil;

import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractStorageTest {

    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

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
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }


    public void fillResume(Resume res){
        res.setContacts(ContactType.PHONE, "+7(921) 855-0482");
        res.setContacts(ContactType.SKYPE, "grigory.kislin");
        res.setContacts(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        res.setContacts(ContactType.GITHUB, "https://github.com/gkislin");
        res.setContacts(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473/gkislin");
        res.setContacts(ContactType.HOMEPAGE, "http://gkislin.ru/");

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

        Experience edu3 = new Experience("Siemens AG","siemens.com");
        edu3.addPeriod(DateUtil.of(2005, Month.JANUARY),DateUtil.of(2005, Month.APRIL) , "3 месяца обучения мобильным IN сетям (Берлин)", null);
        edu.add(edu3);

        Experience edu4 = new Experience("Alcatel","alcatel.com");
        edu4.addPeriod(DateUtil.of(1997, Month.SEPTEMBER),DateUtil.of(2008, Month.MARCH) , "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        edu.add(edu4);

        Experience edu5 = new Experience("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики","www.ifmo.ru");
        edu5.addPeriod(DateUtil.of(1993, Month.SEPTEMBER),DateUtil.of(1996, Month.JULY) , "Аспирантура (программист С, С++)", null);
        edu5.addPeriod(DateUtil.of(1987, Month.SEPTEMBER),DateUtil.of(1993, Month.JULY) , "Инженер (программист Fortran, C)", null);
        edu.add(edu5);
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
        Resume newResume = new Resume(UUID_1, NAME_1);
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
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
