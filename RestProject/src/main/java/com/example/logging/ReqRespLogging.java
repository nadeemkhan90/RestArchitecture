package com.example.logging;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ReqRespLogging implements Filter {
	public volatile  Boolean shouldLog = false;
	private static final int DEFAULT_MAX_PAYLOAD_LENGTH = 50;

	private static final Logger logger = Logger.getLogger(ReqRespLogging.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		shouldLog = true;
	} 

//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		boolean isDebugLoggingEnabled = true;
//			try {
//				 HttpServletResponseWrapper httpServletResponse = null;
//				 BufferedResponseWrapper bufferedResponse = null;
//				if (isDebugLoggingEnabled) {
//					HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//					
//					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//				    BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(httpServletRequest);
//				     httpServletResponse = (HttpServletResponseWrapper) response;
//				     bufferedResponse = new BufferedResponseWrapper(httpServletResponse);
//					
//					final StringBuilder logMessage = new StringBuilder("REST Request - ")
//							.append("[HTTP METHOD:").append(httpServletRequest.getMethod())
//							.append("] [URI:").append(bufferedRequest.getRequestURI())
//							.append("] [USER:").append(auth.getName())
//							.append("] [PATH :").append(bufferedRequest.getPathInfo())
//							.append("] [REQUEST PARAMETERS:").append(httpServletRequest.getParameterMap())
//							.append("] [REQUEST BODY:").append(bufferedRequest.getRequestBody())
//							.append("] [REMOTE ADDRESS:").append(httpServletRequest.getRemoteAddr())
//							.append("]");
//					logger.debug(logMessage.toString()); 
//				}
//				
//				chain.doFilter(request, bufferedResponse);
//
//				if (isDebugLoggingEnabled) {
//					String tranferEncoding = httpServletResponse.getHeader("Transfer-Encoding");
//					if (null == tranferEncoding ||  !tranferEncoding.equalsIgnoreCase("chunked")){
//						logger.debug(" [REST RESPONSE:"+ bufferedResponse.getContent()+"]"); 
//					}
//				}
//			} catch (Exception e) {
//				logger.warn(e.getMessage());
//			}
//		}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isDebugLoggingEnabled = true;
		try {
			if (isDebugLoggingEnabled) {
				HttpServletRequest httpServletRequest = (HttpServletRequest) request;
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();

				final StringBuilder logMessage = new StringBuilder("REST Request - ")
						.append("[THREAD ID:").append(Thread.currentThread().getId())
						.append("] [HTTP METHOD:").append(httpServletRequest.getMethod())
						.append("] [URI:").append(httpServletRequest.getRequestURI())
						.append("] [USER:").append(auth.getName())
						.append("] [PATH :").append(httpServletRequest.getPathInfo())
						.append("] [REQUEST PARAMETERS:").append(httpServletRequest.getParameterMap())
						.append("] [REMOTE ADDRESS:").append(httpServletRequest.getRemoteAddr())
						.append("]");
				logger.fatal(logMessage.toString());
			}

			chain.doFilter(request, response);

			if (isDebugLoggingEnabled) {
				HttpServletResponse httpServletResponse = (HttpServletResponse)response ;
				final StringBuilder logMessage = new StringBuilder(" [REST RESPONSE - ")
						.append("] [THREAD ID:").append( Thread.currentThread().getId())
						.append("[Status: ").append(httpServletResponse.getStatus())
						.append("]");
				logger.fatal(logMessage);
				
			}
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
	}
	@Override
	public void destroy() {
	}

}