package studio.baxia.fo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.pojo.Authors;

/**
 * Created by FirePan on 2016/10/11.
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private IUserService userService;
    String methodName ;
    // 获取当前方法名
    // new Throwable().getStackTrace()[0].getMethodName();

    @Test
    public void testAddAuthors() {
        Integer result = userService.authorsAdd(new Authors("panhainan","123123",1));
        methodName=new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null,result);
    }
    @Test
    public void testEditAuthorsStatus(){
        Boolean result = userService.authorsEditStatus(1, 0);
        methodName=new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null,result);
    }

    @Test
    public void testGetAuthorsById() throws Exception {
        Authors result = userService.authorsGetById(1);
        methodName=new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null,result);
    }

    @Test
    public void testGetAuthorsByAccount() throws Exception {
        Authors result = userService.authorsGetByAccount("panhainan");
        methodName=new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null,result);
    }

    @Test
    public void testGetAuthorsByEmail() throws Exception {
        Authors result = userService.authorsGetByEmail("panhainan@yeah.net");
        methodName=new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null,result);
    }
}
