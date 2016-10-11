package studio.baxia.fo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.pojo.Authors;

import java.util.Date;

/**
 * Created by FirePan on 2016/10/11.
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private IUserService userService;

    // 获取当前方法名
    // new Throwable().getStackTrace()[0].getMethodName();

    @Test
    public void testAddAuthors() {
        Integer result = userService.addAuthors(new Authors("panhainan","123123"));
        System.out.println(printResultStr(
                new Throwable().getStackTrace()[0].getMethodName(), null)
                + result);
    }

}
