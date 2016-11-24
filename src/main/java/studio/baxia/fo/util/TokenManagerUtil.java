package studio.baxia.fo.util;

/**
 * 令牌管理器
 * @author Fire.Pan.
 * @email panhainan@yeah.net
 * @date 2016年4月6日
 */
public interface TokenManagerUtil {

    String createToken(String username);

    boolean checkToken(String token);

    String getUserName(String token);

    boolean deleteToken(String username);
}