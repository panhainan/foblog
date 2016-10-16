package studio.baxia.fo.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import studio.baxia.fo.BaseTest;
import studio.baxia.fo.pojo.Category;

import java.util.List;

/**
 * Created by Pan on 2016/10/16.
 */
public class ICategoryDaoTest extends BaseTest {
    @Autowired
    private ICategoryDao iCategoryDao;

    @Rollback(false)
    @Test
    public void testInsert() {
        Category category = new Category();
        category.setName("Web前端");
        category.setAuthorId(2);
        Integer result = iCategoryDao.insert(category);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

    @Rollback(false)
    @Test
    public void testUpdate() {
        Category category = iCategoryDao.selectById(2);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, category);
        category.setName("修改：" + category.getName());
        category.setParentId(1);
        Integer result = iCategoryDao.update(category);
        printResultStr(methodName, null, result);
    }

    @Test
    public void testDelete() {
        Integer categoryId = 2;
        Category c = iCategoryDao.selectById(categoryId);
        Integer result = null;
        if(c.getParentId()==0){
            //根级别目录
            result = iCategoryDao.deleteBy(1, categoryId);
        }
        result += iCategoryDao.deleteById(categoryId);


        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

    @Test
    public void testSelectByAuthorId() {
        List<Category> result = iCategoryDao.selectBy(1, null);
        methodName = new Throwable().getStackTrace()[0].getMethodName();
        printResultStr(methodName, null, result);
    }

}
