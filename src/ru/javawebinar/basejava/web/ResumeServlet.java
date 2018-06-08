package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.storage = Config.getInstance().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        boolean isNew = (uuid == null || uuid.length() == 0);
        Resume r;
        if (isNew) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
            r.setFullName(fullName);
        }
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                Section section = null;
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        section = new StringField(value);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section = new ListField(request.getParameterValues(type.name()));
                        break;
                    case EXPERIENCE:
                        break;
                    case EDUCATION:
                        break;
                }
                r.addSection(type, section);
            } else {
                r.getSections().remove(type);
            }
        }
        if (isNew) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "add":
                r = new Resume();
                break;
            case "view":
            case "edit":
                r = storage.get(uuid);
                for (SectionType sectionType : SectionType.values()) {
                    Section section = r.getSection(sectionType);
                    switch (sectionType) {
                        case PERSONAL:
                        case OBJECTIVE:
                            if (section == null) {
                                section = new StringField("");
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = new ListField("");
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            break;
                    }
                    r.addSection(sectionType,section);
                }
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(getAction(action)).forward(request, response);
    }

    private String getAction(String action) {
        String jspPath = "";
        switch (action) {
            case "add":
                jspPath = "/WEB-INF/jsp/add.jsp";
                break;
            case "edit":
                jspPath = "/WEB-INF/jsp/edit.jsp";
                break;
            case "view":
                jspPath = "/WEB-INF/jsp/view.jsp";
        }
        return jspPath;
    }
}