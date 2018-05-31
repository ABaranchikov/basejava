package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.WebHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class ResumeServlet extends HttpServlet {

    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.storage = Config.getInstance().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        Writer out = response.getWriter();
        out.write("<html>\n" +
                "<head>\n" +
                "<title>Resume</title>\n" +
                "</head>\n" +
                "<body>\n"
        );

        String uuid = request.getParameter("uuid");
        if (uuid == null) {
            for (Resume resume : storage.getAllSorted()) {
                WebHelper.printResume(out, resume);
            }
        } else {
            Resume resume = storage.get(uuid);
            WebHelper.printResume(out, resume);
        }
        out.write("</body>\n" +
                "</html>");
    }
}