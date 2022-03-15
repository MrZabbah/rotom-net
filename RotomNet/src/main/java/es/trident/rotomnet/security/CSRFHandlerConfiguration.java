package es.trident.rotomnet.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@SuppressWarnings("deprecation")
@Configuration
public class CSRFHandlerConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CSRFHandlerInterceptor());
	}
	
}

@SuppressWarnings("deprecation")
class CSRFHandlerInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void postHandle(final HttpServletRequest req, final HttpServletResponse response, 
			final Object handler, final ModelAndView modelAndView) throws Exception{		
		CsrfToken token = (CsrfToken) req.getAttribute("_csrf");
		modelAndView.addObject("token", token.getToken());
	}
}
