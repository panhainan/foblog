package studio.baxia.fo.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.TreeInfoResult;

/**
 * Created by Pan on 2016/10/16.
 */
public class IArticleServiceTest extends BaseTest {
    @Autowired
    private IBlogService iArticleService;
    @Test
    public void testMessageGetAllBy() throws Exception {
        TreeInfoResult tree = iArticleService.messageGetAllBy(1, CommonConstant.CORRECT_ORDER);
        System.out.println(tree);
    }
}
