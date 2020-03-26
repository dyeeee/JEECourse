package cn.edu.swu;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.LogRecord;
import java.io.IOException;

//实现 Filter 类
public class LogFilter extends HttpFilter {

    private Writer mywriter = null;
    private SimpleDateFormat dateFormater= null;


    @Override
    public void  init(FilterConfig config) throws ServletException {
        // 获取初始化参数
        String p1 = config.getInitParameter("param1");

        // 输出初始化参数
        System.out.println(p1);

        File logFile = new File("/Users/YES/Documents/apache-tomcat-9.0.31/webapps/MyServletTest2/myapp.log");
        try {
            this.mywriter = new FileWriter(logFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void  doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do LogFilter");

        //此时的对象是ServletRequest 没有特定的协议，因此还获取不了一些url信息
        //可以强行转换
        //((HttpServletRequest)request).getRequestURI();
        //也可以将实现改为extend HttpFilter
        String url = request.getRequestURI();
        System.out.println(url);

        String address = request.getRemoteAddr();
        //String date = this.dateFormater.format(Calendar.getInstance().getTime());

        // 写入日志
        this.mywriter.append(String.format("%s %s\n",  address, url));
        //this.mywriter.append(url + "\n");
        //无论缓冲区中有多少内容，都写入,比较影响性能，一般写在close时
        this.mywriter.flush();


        // 把请求传回过滤链, 把请求继续往后发送
        chain.doFilter(request,response);
    }

    @Override
    public void destroy( ){
        /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
        System.out.println("LogFilter destroy");

        try {
            this.mywriter.flush(); //无论缓冲区中有多少内容，都写
            this.mywriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}