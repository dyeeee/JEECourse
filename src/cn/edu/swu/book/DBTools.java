package cn.edu.swu.book;

import cn.edu.swu.book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTools {

    //查询所有
    public static List<Book> listBook(){
        return queryBook("SELECT * FROM book");
    }

    //ID查询
    public static Book getBookById(int bookid) {
        String sql = String.format("SELECT * FROM book WHERE id=%s", bookid);
        List<Book> books = queryBook(sql);

        return books.size() > 0 ? books.get(0) : null;
    }

    public static List<Book> getBookByName(String keyword) {
        return queryBook("SELECT * FROM book WHERE name LIKE '%" + keyword + "%'");
    }

    //查询语句
    public static List<Book> queryBook(String sql) {
        //返回查询到的书本列表
        List<Book> books = new ArrayList<Book>();

        //加载数据库驱动程序
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("ok");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }

        //这个就是数据库的位置
        String dburl = "jdbc:mysql://127.0.0.1:3306/JEECourse";

        try {
            Connection conn = DriverManager.getConnection(dburl,"root","duyeduye");
            System.out.println("Connected Success");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");

                System.out.println(id +" "+name+" "+price+" ");

                Book book = new Book();
                book.setId(id);
                book.setName(name);
                book.setPrice(price);

                books.add(book);
            }

            stmt.close();
            conn.close();
            return books;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    //添加
    public static int addBook(String name, float price) {
        String sql = String.format("INSERT INTO book(name, price) values('%s', %f)", name, price);
        System.out.println(sql);
        return updateBook(sql);
    }

    //删除
    public static int delBook(int bookid) {
        String sql = String.format("DELETE FROM book where id=%s", bookid);
        System.out.println(sql);
        return updateBook(sql);
    }

    public static int updateBook(Book book) {
        //将book对象的name price作为参数，根据id修改
        String sql = String.format("UPDATE book SET name='%s', price=%s WHERE id=%s",
                book.getName(), book.getPrice(), book.getId());
        System.out.println(sql);
        return updateBook(sql);
    }


    //更新
    public static int updateBook(String sql) {
        //加载数据库驱动程序
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }

        //这个就是数据库的位置
        String dburl = "jdbc:mysql://127.0.0.1:3306/JEECourse";

        try {
            Connection conn = DriverManager.getConnection(dburl,"root","duyeduye");
            System.out.println("Connected Success");
            Statement stmt = conn.createStatement();

            // 修改了多少条数据
            int count = stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    //显示书本，传入书本类列表，转换成html表格形式
    public static String books2Table(List<Book> books) {
        String html = "<br><br><center>"
                + "<a href='./listBook'>All Book</a> &nbsp;&nbsp;&nbsp;&nbsp; "
                + "<a href='./newbook.html'>New Book</a> &nbsp;&nbsp;&nbsp;&nbsp; "
                + "<a href='./searchbook.html'>Search Book</a>  "
                + "<br><br>"
                + "<table border='1' width='50%'>";
        html += "<tr><th>ID</th><th>Name</th><th>Price</th><th></th><th></th></tr>";

        for (Book book : books) {
            html += String.format(
                    "<tr><td align='center'>%s</td><td>%s</td><td align='center'>%s</td>"
                            + "<td align='center'><a href='./deleteBook?bookid=%s'>Delete</a></td>"
                            + "<td align='center'><a href='./editBook?bookid=%s'>Edit</a></td>"
                            + "</tr>",
                    String.valueOf(book.getId()),
                    book.getName(),
                    String.valueOf(book.getPrice()),
                    book.getId(),
                    book.getId()
            );
        }

        html += "</table></center>";

        return html;
    }

}
