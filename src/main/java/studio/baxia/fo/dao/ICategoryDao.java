package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.pojo.Category;

import java.util.List;

/**
 * Created by Pan on 2016/10/16.
 */
@Repository("iCategoryDao")
public interface ICategoryDao {

    /**
     * 插入类别
     * @param category 类别（parent_id,name,author_id）
     * @return 受影响的行数
     */
    Integer insert(Category category);

    /**
     * 更新类别
     * @param category 类别（id,authorId,parentId,name）(前两个字段用于验证该用户具备权限，后两个字段为小更新的字段)
     * @return 受影响的行数
     */
    Integer update(Category category);

    /**
     * 删除类别
     * @param categoryIds 类别id集合
     * @return 受影响的行数
     */
    Integer delete(@Param("ids")List<Integer> categoryIds);

    /**
     * 通过id删除类别
     * @param categoryId 类别id
     * @return  受影响的行数
     */
    Integer deleteById(@Param("id")Integer categoryId);


    /**
     * 通过作者id和父类别id删除类别
     * @param categoryAuthorId 作者id
     * @param categoryParentId 父类别id
     * @return 受影响的行数
     */
    Integer deleteBy(@Param("authorId")Integer categoryAuthorId,@Param("parentId")Integer categoryParentId);

    /**
     * 通过类别id查找
     * @param categoryId 类别id
     * @return Category
     */
    Category selectById(@Param("id")Integer categoryId);

    /**
     * 通过作者id查找
     * @param categoryAuthorId 作者id（不允许为null）
     * @param categoryParentId 类别父id（null：查找作者的所有类别）
     * @return
     */
    List<Category> selectBy(@Param("authorId")Integer categoryAuthorId,@Param("parentId")Integer categoryParentId);

}
