package com.netbanking.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

@WebFilter
public class SessionCheck implements Filter {

	@Override
	public void doFilter(ServletRequest httpRequest, ServletResponse httpResponse, FilterChain filterChain) 
		throws IOException, ServletException
    {
		HttpServletResponse resp = (HttpServletResponse) httpResponse;
		try {
		resp.setHeader("Expires", "Tue, 03 Jul 2001 06:00:00 GMT");
        resp.setHeader("Last-Modified", new Date().toString());
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
        resp.setHeader("Pragma", "no-cache");
        
        filterChain.doFilter(httpRequest, httpResponse);
		} catch (MaxUploadSizeExceededException e) {
            System.out.println("File size exceeded!");
            resp.sendRedirect("youShouldNotBeHere.jsp");
        }
    }

	@Override
	public void destroy() {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
