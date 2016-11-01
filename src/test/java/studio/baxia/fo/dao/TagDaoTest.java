package studio.baxia.fo.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.pojo.Tag;

import java.util.List;

/**
 * Created by Pan on 2016/10/16.
 */
public class TagDaoTest extends BaseTest {

    @Autowired
    private ITagDao iTagDao;

    @Rollback(false)
    @Test
    public void testInsert() {
        Tag tag = new Tag();
        tag.setName("Java");
        Integer result = iTagDao.insert(tag);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

    @Test
    public void testDelete() {
        Integer result = iTagDao.delete(1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }
    @Test
    public void testSelectBy(){
        List<Tag> result = iTagDao.selectBy();
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }
    @Test
    public void testSelectById(){
        Tag result = iTagDao.selectById(1);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }
}
