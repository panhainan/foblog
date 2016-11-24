package studio.baxia.fo.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class CorsFilter implements Filter {

	private String allowOrigin;
	private String allowMethods;
	private String allowCredentials;
	private String allowHeaders;
	private String exposeHeaders;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (null!=allowOrigin) {
			List<String> allowOriginList = Arrays
					.asList(allowOrigin.split(","));
			if (allowOriginList!=null&& !allowOriginList.isEmpty()) {
				String currentOrigin = request.getHeader("Origin");
				if (allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin",
							currentOrigin);
				}
			}
		}
		if (null!=allowMethods) {
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (null!=allowCredentials) {
			response.setHeader("Access-Control-Allow-Credentials",
					allowCredentials);
		}
		if (null!=allowHeaders) {
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (null!=exposeHeaders) {
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
}