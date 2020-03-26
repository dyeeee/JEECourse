package cn.edu.swu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{

        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy/mm/dd, HH:mm:ss");

        Date date =new Date();

        PrintWriter out = response.getWriter();
        out.println(simpleDateFormat.format(date));
    }
}
