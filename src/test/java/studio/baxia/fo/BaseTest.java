/**
 * 
 */
package studio.baxia.fo;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author fireoct
 * @email panhainan@yeah.net
 * @date 2016-09-09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/**/applicationContext.xml"})
public class BaseTest {
	public String printResultStr(String methodName, String resultExplain) {
		if (null != resultExplain) {
			return "|| 测试类 '" + this.getClass() + "' 的测试方法 '" + methodName
					+ "' 的测试结果为（解释：" + resultExplain + "）：";
		} else {
			return "|| 测试类 '" + this.getClass() + "' 的测试方法 '" + methodName
					+ "' 的测试结果为：";
		}

	}
}
