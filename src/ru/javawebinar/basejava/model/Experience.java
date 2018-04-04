package ru.javawebinar.basejava.model;

public class Experience {
    private String company;
    private String dateFrom;
    private String dateTo;
    private String position;
    private String duties;

    public Experience(String company, String dateFrom, String dateTo, String position, String duties) {
        this.company = company;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.position = position;
        this.duties = duties;
    }

    public String toString() {

        return "Company: " +
                company +
                "\n" +
                "From: " +
                dateFrom +
                "\n" +
                "To: " +
                dateTo +
                "\n" +
                "Position: " +
                position +
                "\n" + (duties == null ? "" : ("Duties: " +
                duties));
    }
}
