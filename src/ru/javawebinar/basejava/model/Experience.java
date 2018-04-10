package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Experience {
    private final Link homePage;

    private static class Periods{
        private final LocalDate dateFrom;
        private final LocalDate dateTo;
        private final String title;
        private final String description;

        Periods(LocalDate dateFrom, LocalDate dateTo, String title, String description){

            Objects.requireNonNull(dateFrom, "DateFrom must not be null");
            Objects.requireNonNull(dateTo, "DateTo must not be null");

            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.title = title;
            this.description = description;
        }

        @Override
        public int hashCode() {
            int result = dateFrom.hashCode();
            result = 31 * result + dateTo.hashCode();
            result = 31 * result + title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            return result;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Periods that = (Periods) o;

            if (!dateFrom.equals(that.dateFrom)) return false;
            if (!dateTo.equals(that.dateTo)) return false;
            if (!title.equals(that.title)) return false;
            return description != null ? description.equals(that.description) : that.description == null;
        }
    }

    private List<Periods> periods = new ArrayList<>();

    public Experience(String name, String url) {

        this.homePage = new Link(name, url);

    }

    public void addPeriod(LocalDate dateFrom, LocalDate dateTo, String title, String description){
        Objects.requireNonNull(title, "title must not be null");
        periods.add(new Experience.Periods(dateFrom, dateTo, title, description));
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(homePage).append("\n");
        for (Periods period: periods){
            sb.append("From: ").append(period.dateFrom).append("To: ").append(period.dateTo).append("\n");
            sb.append("Position: ").append(period.title).append("\n");
            if (period.description != null) {
                sb.append("Duties:").append(period.description).append("\n");
            }

        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;

        if (!homePage.equals(that.homePage)) return false;
        return periods.equals(((Experience) o).periods);
    }

    @Override
    public int hashCode() {

        int result = homePage.hashCode();
        for (Experience.Periods period: periods) {
            result += period.hashCode();
        }
        return result;
    }
}
