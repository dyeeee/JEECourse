package cn.edu.swu.book;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.util.List;


//将数据库中的书本打印到web界面上
public class ListBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从数据库获取书本类列表
        List<Book> books = DBTools.listBook();
        System.out.println(books);

        //将书本列表转换为html表格
        String html = DBTools.books2Table(books);

        System.out.println(html);

        Writer writer = response.getWriter();
        writer.write(html);
        writer.flush();
    }

}
