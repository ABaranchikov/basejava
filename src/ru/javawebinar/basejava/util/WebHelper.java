package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class WebHelper {

    public static void printResume(Writer out, Resume resume) throws IOException {
        out.write("<h1>" + resume.getFullName() + "</h1>\n");
        out.write("<h1>" + resume.getUuid() + "</h1>\n");
        out.write("<p>\n");

        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            out.write("<a href=" + entry.getValue() + ">" + entry.getValue() + "</a><br/>\n");

        }
        out.write("<p>\n");
        out.write("<hr>\n");

        out.write("<table BORDER=0 CELLPADDING=2>\n");

        for (Map.Entry<SectionType, Section> entry : resume.getSections().entrySet()) {
            SectionType sectionType = entry.getKey();
            Section values = entry.getValue();

            out.write("<tr>");
            out.write("<td colspan=2><h2>" + sectionType.getTitle() + "</h2></td>\n");
            out.write("</tr>\n");

            switch (sectionType) {
                case PERSONAL:
                case OBJECTIVE:
                    out.write("<tr>\n");
                    out.write("<td colspan=2>\n");
                    out.write("<h3>" + values.toString() + "</h3>\n");
                    out.write("</td>\n");
                    out.write("</tr>\n");
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    out.write("<tr>\n");
                    out.write("<td colspan=2>\n");
                    out.write("<ul>\n");
                    for (String item : ((ListField) values).getItems()) {
                        out.write("<li>" + item + "</li>\n");
                    }
                    out.write("</ul>\n");
                    out.write("</tr>\n");
                    break;
                case EXPERIENCE:
                    break;
                case EDUCATION:
                    break;
            }
        }
        out.write("</table>\n");
    }
}