/**
 *
 */
package studio.baxia.fo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fireoct
 * @email panhainan@yeah.net
 * @date 2016-09-09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/**/applicationContext.xml"})
// 配置事务的回滚,对数据库的增删改都会回滚,便于测试用例的循环利用
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BaseTest {
    public String methodName;
    public String printResultStr(String methodName, String resultExplain, Object resultData) {
        StringBuffer resultStr = new StringBuffer("**************************************************************\n");
        resultStr.append("测试类 '" + this.getClass() + "' 的测试方法 '" + methodName + "' 的测试结果为:\n");
        if (null != resultExplain) {
            resultStr.append("（解释：" + resultExplain + "）\n");
        }
        resultStr.append(resultData);
        resultStr.append("\n**************************************************************");
        System.out.println(resultStr);
        return resultStr.toString();
    }
}
