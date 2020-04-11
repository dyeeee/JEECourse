package cn.edu.swu;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessFilter extends HttpFilter {

    @Override
    public void  doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        String url = request.getRequestURI();

        //如果拦截所有的话，用户直接输入.login.html，就会不停的重定向。要获取用户请求访问资源的URI，
        // loginIndex.html或者是login<Servlet>的话就不拦截，直接返回放行，不执行后续的拦截
        if (url.endsWith("LoginIndex.html") || url.endsWith("login")) {
            chain.doFilter(request, response);
            return;
        }

        //取得用户请求的session，获取session的属性loginFlag
        //如果session不为空且session的FLAG不为空且满足TOKEN值，则放行
        //否则重定向到登陆界面
        if (session != null && session.getAttribute(LoginServlet.LOGIN_FLAG) != null &&
                session.getAttribute(LoginServlet.LOGIN_FLAG).equals(LoginServlet.LOGIN_TOKEN)	) {
            chain.doFilter(request, response);
        } else {
            System.out.println("Invalide user, Access denied, please login\n\n");
            response.sendRedirect("./LoginIndex.html");
            System.out.println("sendRedirect done");
        }

//        // 把请求传回过滤链, 把请求继续往后发送
//        chain.doFilter(request,response);
    }


}
