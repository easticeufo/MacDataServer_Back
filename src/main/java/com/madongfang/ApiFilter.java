package com.madongfang;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Servlet Filter implementation class ApiFilter
 */
@Component
public class ApiFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ApiFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		
		httpResponse.setContentType("application/json");
		httpResponse.setCharacterEncoding("UTF-8");
		
		String uri = httpRequest.getRequestURI();
		String query = httpRequest.getQueryString();
		if (query != null)
		{
			logger.debug("ApiFilter: method=" + httpRequest.getMethod() + ", url=" + uri + "?" + query);
		}
		else
		{
			logger.debug("ApiFilter: method=" + httpRequest.getMethod() + ", url=" + uri);
		}
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private final Logger logger = LoggerFactory.getLogger(getClass());
}
