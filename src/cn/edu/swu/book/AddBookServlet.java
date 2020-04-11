package cn.edu.swu.book;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数name和price
        String name = request.getParameter("name");
        String price = request.getParameter("price");

        //禁止输入为空的参数
        if (name == null || name.isEmpty() || price == null || price.isEmpty()) {
            throw new IOException("Wrong parameter for create a new book");
        }

        DBTools.addBook(name, Float.valueOf(price));

        //重定向回listBookServlet
        response.sendRedirect("./listBook");
    }
}
