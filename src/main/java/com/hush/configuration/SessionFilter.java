package com.hush.configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SessionFilter implements Filter {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    /*@Value("$(serverurl)")
    private String serverurl;*/
    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();
    


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        //System.out.println(url);
        /*if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }*/
        if (isInclude(url)){
            chain.doFilter(httpRequest, httpResponse);
            return;
        }if(url.contains("member/insert")) {
        	chain.doFilter(httpRequest, httpResponse);
            return;
        }if(url.contains("member/compare")) {
        	chain.doFilter(httpRequest, httpResponse);
            return;
        }
        if(url.contains("file/upload")) {
        	chain.doFilter(httpRequest, httpResponse);
            return;
        }if(url.contains("comm/comlist")) {
        	chain.doFilter(httpRequest, httpResponse);
            return;
        }if (url.contains("page/login")) {
        	chain.doFilter(httpRequest, httpResponse);
            return;
		}
        else {
            HttpSession session = httpRequest.getSession();
            if (session.getAttribute("userName") != null){
                // session存在
                chain.doFilter(httpRequest, httpResponse);
                return;
            } else {
                // session不存在 准备跳转失败
                httpResponse.sendRedirect("/page/login");
            }
        }
    }

    @Override
    public void destroy() {

    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

	@Override
	public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        patterns.add(Pattern.compile("(.*)page/register(.*)"));
        patterns.add(Pattern.compile("(.*)member/insert(.*)"));//localhost:8070//member/insert
        patterns.add(Pattern.compile("(.*)file/upload(.*)"));
        patterns.add(Pattern.compile("(.*)comm/comlist(.*)"));
        patterns.add(Pattern.compile(".*[(\\.js)||(\\.css)||(\\.png)]"));
		
	}



}