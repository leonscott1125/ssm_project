package filter;

import core.MyRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class RequestWraperFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截器生效");
        HttpServletRequest httpServletRequest= (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        MyRequest request = new MyRequest(httpServletRequest,httpServletResponse);
        HttpSession session = request.getSession();
        System.out.println(session.getId()+"拦截器");
        //将请求替换成自己创建的请求
        filterChain.doFilter(request,httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
