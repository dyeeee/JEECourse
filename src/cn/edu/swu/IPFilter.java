

package cn.edu.swu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPFilter extends HttpFilter {

    private String allowedAddress = null;

    public void init(FilterConfig config) throws ServletException {
        System.out.println("IPFilter Initialize");
        this.allowedAddress = config.getInitParameter("address");
    }

    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("do IPFilter");
        String address = request.getRemoteAddr();
        System.out.println("addr: " + address);
        if (this.allowedAddress.contains(address)){
            chain.doFilter(request, response);
        }else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Please access Admin Console with local host address");
        }
    }

    public void destroy() {
        System.out.println("IPFilter destroy");
    }
}
