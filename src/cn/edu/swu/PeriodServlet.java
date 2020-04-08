package cn.edu.swu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//观察Servlet生命周期的Servlet
public class PeriodServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{
        super.init();  //重载父类函数，抛出异常
        // 懒加载 初始化没人用时，不会去加载
        // 在webxml中
        // <load-on-startup>1</load-on-startup>
        // 直接加载
        System.out.println("Initialize PeriodServlet");
    }

    public void service(HttpServletRequest request, HttpServletResponse response) {
        //super.service(); 直接接受请求，不用调用父类
        //有两个父类service，一个是ServletRequest，一个是Http
        System.out.println("Do Service");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("Destroied");
    }
}