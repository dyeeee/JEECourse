package cn.edu.swu.book;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookid = request.getParameter("bookid");
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        if (name == null || name.isEmpty() || price == null || price.isEmpty()) {
            throw new IOException("Wrong parameter for edit a book");
        }

        Book book = new Book();
        book.setId(Integer.valueOf(bookid));
        book.setName(name);
        book.setPrice(Float.valueOf(price));

        DBTools.updateBook(book);

        response.sendRedirect("./listBook");
    }
}