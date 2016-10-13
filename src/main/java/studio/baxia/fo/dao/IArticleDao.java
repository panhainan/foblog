package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.pojo.Article;

import java.util.List;

/**
 * Created by Pan on 2016/10/13.
 */
@Repository(value = "iArticleDao")
public interface IArticleDao {

    /**
     * 插入文章信息（待编写sql实现）
     * @param article 文章信息（）
     * @return id
     */
    Integer insert(Article article);

    /**
     * 通过id查找（待编写sql实现）
     * @param articleId 文章id
     * @return Article
     */
    Article selectById(@Param("id") Integer articleId);

    List<Article> selectByTitle(@Param("title") String articleTitle, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<Article> selectByAuthorsId(@Param("authorsId") Integer articleAuthorsId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<Article> selectByCategoryId(@Param("categoryId")String articleCategoryId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    List<Article> selectByTagIds(@Param("tagId")String articleTagId, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);

    /**
     * 通过标题、作者id、类别id、标签id进行模糊查找
     * 注意：categoryId和tagId的值用"id/"表示，比如"1/"，因为规定数据库中存储的格式为id1/id2/id3/方式存储
     * @param article 文章信息（此处可选包括：title、authorId、categoryIds、tagIds）
     * @param pageConfig 分页信息（此处可选包括：pageIndex、pageSize）
     * @return
     */
    List<Article> selectBy(@Param("article") Article article, @Param("pageConfig") PageConfig pageConfig);

    /**
     * 通过标题、作者id、类别id、标签id进行模糊查找取得所有记录数量。
     * 注意：参照"selectBy"方法的注意事项，用于配合"selectBy"方法使用
     * @param article 文章信息（此处可选包括：title、authorId、categoryIds、tagIds）
     * @return
     */
    Integer selectCountBy(Article article);

}
