package cn.edu.swu.book;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bookid = request.getParameter("bookid");
        Book book = DBTools.getBookById(Integer.valueOf(bookid));

        if (book == null ) {
            response.sendRedirect("./listBook");
        } else {
            String html = String.format("<center>\r\n" +
                    "		<br><br>\r\n" +
                    "		Input a New Book\r\n" +
                    "		<br><br>\r\n" +
                    "		<form action=\"./updateBook\" method=\"POST\" >\r\n" +
                    "			        <input type=\"hidden\"  name=\"bookid\" value=\"%s\" />\r\n" +
                    "			Name :  <input type=\"text\"  name=\"name\" value=\"%s\" />\r\n" +
                    "			Price : <input type=\"text\" name=\"price\" value=\"%s\" />\r\n" +
                    "			<input type=\"submit\"/>\r\n" +
                    "		</form>\r\n" +
                    "	</center>", book.getId(), book.getName(), book.getPrice());

            response.setContentType("text/html");
            Writer writer = response.getWriter();
            writer.write(html);
            writer.flush();
        }

    }
}
