package studio.baxia.fo.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import studio.baxia.fo.util.ExecuteSecurity;
import studio.baxia.fo.util.TokenManagerUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 用于检查 token 的拦截器
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

    private static final String DEFAULT_TOKEN_NAME = "X-Token";

    private TokenManagerUtil tokenManager;
    private String tokenName;

    public void setTokenManager(TokenManagerUtil tokenManager) {
        this.tokenManager = tokenManager;
    }

    public void setTokenName(String tokenName) {
        System.out.println(tokenName);
        if (tokenName==null) {
            tokenName = DEFAULT_TOKEN_NAME;
        }
        this.tokenName = tokenName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    	System.out.println("进入token拦截器");
        // 从切点上获取目标方法
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 若目标方法忽略了安全性检查，则直接调用目标方法
        if (!method.isAnnotationPresent(ExecuteSecurity.class)) {
            return true;
        }
        // 从 request header 中获取当前 token
        String token = request.getHeader(tokenName);

        // 检查 token 有效性
        if (!tokenManager.checkToken(token)) {
            String message = String.format("token [%s] is invalid", token);
            System.out.println(message);
            response.addHeader("x-login", "true");
//            throw new TokenException(message);
            return false;
        }
        // 调用目标方法
        return true;
    }
}
