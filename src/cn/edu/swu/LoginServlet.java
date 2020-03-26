package cn.edu.swu;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;

public class LoginServlet extends HttpServlet {

    private String adminUser = null;
    private String adminPass = null;

    public final static String LOGIN_FLAG  = "LOGIN_TAG";
    public final static String LOGIN_TOKEN  = "1234567";


    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);

        //在init时取到初始化参数，现在可以自己在xml中配置账号和密码
        this.adminUser = config.getInitParameter("admin_user");
        this.adminPass = config.getInitParameter("admin_pass");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //从请求里获取参数
        String user = request.getParameter("user");
        String password = request.getParameter("pass");

        response.setContentType("text/html");
        Writer writer = response.getWriter();
        if (user.equals(this.adminUser) && password.equals(this.adminPass)) {
            System.out.println(user + password);
            writer.write("<h2>Welcome " + user + " login</h2>");

            HttpSession session = request.getSession(true);
            session.setAttribute(LOGIN_FLAG, LOGIN_TOKEN);

            response.sendRedirect("index.html");

        } else {
            System.out.println(user + password);
            //这时候这一句已经不显示了，因为重定向了
            writer.write("<h2 style='color:red'>Failed</h2>");
            System.out.println("Wrong username or password");
            response.sendRedirect("LoginIndex.html");
        }
    }
}
