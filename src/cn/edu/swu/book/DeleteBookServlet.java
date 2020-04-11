package cn.edu.swu.book;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookid = request.getParameter("bookid");

        //html来的都是String 要做强制类型转换
        DBTools.delBook(Integer.valueOf(bookid));

        response.sendRedirect("./listBook");
    }
}
