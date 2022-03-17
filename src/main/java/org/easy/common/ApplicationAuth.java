package org.easy.common;

//import eu.bitwalker.useragentutils.Browser;
//import eu.bitwalker.useragentutils.OperatingSystem;
//import eu.bitwalker.useragentutils.UserAgent;
import org.easy.tool.support.CacheMap;
import org.easy.tool.web.R;
import org.easy.user.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ApplicationAuth implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer  {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationAuth.class);


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/system/**","/word/**").excludePathPatterns("/login");
	}


	@Bean
    public HandlerInterceptor authenticationInterceptor() {

		return new HandlerInterceptor() {

			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

				UserVO user= (UserVO) request.getSession().getAttribute(request.getHeader(ApplicationAttribute.AUTHORIZED_ID));
				if(user!=null){
					return true;
				}
				if(request.getMethod().equals("OPTIONS")){
					response.setStatus(HttpServletResponse.SC_OK);
				}else {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
				response.getWriter().println(R.fail("NOT LOGIN"));
				return false;
			}


		};
	}



}
