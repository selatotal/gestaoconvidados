package br.edu.ulbra.gestaoconvidados.components;

import br.edu.ulbra.gestaoconvidados.entities.User;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

@Component
public class URLFilter implements Filter {

    private ArrayList<String> urlList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = "/,/login,/forgotPassword,,/console/*";
        StringTokenizer token = new StringTokenizer(urls, ",");

        urlList = new ArrayList<String>();

        while (token.hasMoreTokens()) {
            urlList.add(token.nextToken());

        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest https = (HttpServletRequest)servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        boolean allowedRequest = false;
        String url = https.getServletPath();

        if (urlList.contains(url)){
            allowedRequest = true;
        }

        if (!allowedRequest) {
            HttpSession session = https.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                ((HttpServletResponse) servletResponse).sendRedirect("/");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}
