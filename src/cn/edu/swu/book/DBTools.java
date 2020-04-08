package cn.edu.swu.book;

import cn.edu.swu.book.Book;

import java.util.ArrayList;
import java.util.List;

public class DBTools {

    //查询
    public List<Book> listBook(){
        List<Book> books = new ArrayList<Book>();



        return books;
    }

    //添加

    //更新
    public static int updateDook(String sql){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException cne){
            cne.printStackTrace();
        }

        return 0;
    }

}
