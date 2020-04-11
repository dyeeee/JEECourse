package cn.edu.swu.book;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        List<Book> books =  DBTools.getBookByName(name);
        String html = DBTools.books2Table(books);

        Writer writer = response.getWriter();
        writer.write(html);
        writer.flush();
    }
}
