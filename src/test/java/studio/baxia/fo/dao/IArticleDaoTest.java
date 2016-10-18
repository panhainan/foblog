package studio.baxia.fo.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.common.Constant;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;

import java.util.Date;
import java.util.List;

/**
 * Created by Pan on 2016/10/13.
 */
public class IArticleDaoTest extends BaseTest {

    @Autowired
    private IArticleDao iArticleDao;

    // 有些单元测试你不希望回滚
    @Rollback(false)
    @Test
    public void testInsert() {
        Article article = new Article();
        article.setTitle("博客初建" + Math.random());
        article.setSummary("本人博客开篇，网站初建，欢迎大家关注，多交流。" + Math.random());
        article.setContent("hello，大家好，欢迎来到我的博客网站，希望大家多沟通交流。谢谢大家。" + Math.random());
        article.setAuthorId(1);
        article.setCategoryIds("1/5/6/");
        article.setTagIds("1/");
        article.setStatus(Constant.ACTICLE_STATUS_DRAFT);
        article.setWriteTime(new Date());
//        article.setPubTime(new Date());
        Integer result = iArticleDao.insert(article);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
        System.out.println(article);
    }

    @Test
    public void testDelete() {
        Integer result = iArticleDao.delete(1,1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        Article article = iArticleDao.selectById(1,1);
        if (article != null) {
            article.setSummary("修改：本人博客开篇，网站初建，欢迎大家关注，多交流。");
            article.setContent("修改：hello，大家好，欢迎来到我的博客网站，希望大家多沟通交流。谢谢大家。" + Math.random());
            article.setAuthorId(1);
            article.setCategoryIds("1/5/6/8/");
            article.setTagIds("1/6/7/");
            article.setStatus(Constant.ACTICLE_STATUS_BLOG);
            article.setPubTime(new Date());
            Integer result = iArticleDao.update(article);
            methodName = new Throwable().getStackTrace()[0].getMethodName();
            printResultStr(methodName, null, result);
        }

    }

    @Test
    public void testSelectById() {
        Article result = iArticleDao.selectById(1,1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

    @Test
    public void testSelectCountBy() {
        Article article = new Article();
        article.setTitle("博客初建");
        article.setAuthorId(1);
        article.setCategoryIds("5/");
        article.setTagIds("1/");
        Integer result = iArticleDao.selectCountBy(article);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

    @Test
    public void testSelectBy() {
        Article article = new Article();
        article.setTitle("博客初建");
        article.setAuthorId(1);
        article.setCategoryIds("5/");
        article.setTagIds("1/");
        article.setStatus(Constant.ACTICLE_STATUS_BLOG);
        PageConfig pageConfig = new PageConfig(1, 2);
        List<Article> result = iArticleDao.selectBy(article, pageConfig);
        Integer resultCount = iArticleDao.selectCountBy(article);
        PageInfoResult<Article> pageInfoResult = new PageInfoResult(result, pageConfig, resultCount);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, pageInfoResult);
    }


}
