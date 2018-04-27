package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.util.DateUtil;
import ru.javawebinar.basejava.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import static ru.javawebinar.basejava.util.DateUtil.NOW;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Experience implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;

    private List<Periods> periods = new ArrayList<>();

    public Experience() {
    }

    public Experience(String name, String url, Periods... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Experience(Link homePage, List<Periods> periods) {
        this.homePage = homePage;
        this.periods = periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Periods> getPeriods() {
        return new ArrayList<>(periods);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "homePage=" + homePage +
                ", periods=" + periods +
                '}';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Periods implements Serializable {
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateFrom;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate dateTo;
        private String title;
        private String description;

        public Periods() {
        }

        public Periods(int startYear, Month startMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), NOW, title, description);
        }

        public Periods(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(DateUtil.of(startYear, startMonth), DateUtil.of(endYear, endMonth), title, description);
        }

        public Periods(LocalDate dateFrom, LocalDate dateTo, String title, String description) {

            Objects.requireNonNull(dateFrom, "DateFrom must not be null");
            Objects.requireNonNull(dateTo, "DateTo must not be null");
            Objects.requireNonNull(title, "title must not be null");

            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return dateFrom;
        }

        public LocalDate getEndDate() {
            return dateTo;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return "Periods{" +
                    "dateFrom=" + dateFrom +
                    ", dateTo=" + dateTo +
                    ", title='" + title +
                    ", description='" + description + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Periods periods = (Periods) o;
            return Objects.equals(dateFrom, periods.dateFrom) &&
                    Objects.equals(dateTo, periods.dateTo) &&
                    Objects.equals(title, periods.title) &&
                    Objects.equals(description, periods.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dateFrom, dateTo, title, description);
        }

    }
}
