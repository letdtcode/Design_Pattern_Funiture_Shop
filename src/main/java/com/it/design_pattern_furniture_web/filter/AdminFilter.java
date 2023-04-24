package com.it.design_pattern_furniture_web.filter;


import com.it.design_pattern_furniture_web.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        httpReq.setCharacterEncoding("UTF-8");
        httpResp.setCharacterEncoding("UTF-8");
        HttpSession session = httpReq.getSession(false);
//        UserViewModel user = null;
//        if(session != null)
//            user = (UserViewModel) session.getAttribute("admin");
//        if(user != null){
//            chain.doFilter(request, response);
//        }else{
//            httpReq.setAttribute("error","error");
//            ServletUtils.forward(httpReq, httpResp, "/admin/login");
//        }
    }
}
