package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.vo.ArchiveVo;
import studio.baxia.fo.vo.ArticleVo;

import java.util.List;

/**
 * Created by Pan on 2016/10/13.
 */
@Repository(value = "iArticleDao")
public interface IArticleDao {

	/**
	 * 插入文章信息（待编写sql实现）
	 * 
	 * @param article
	 *            文章信息（title,summary,content,categoryIds,tagIds,status,writeTime
	 *            ,pubTime）
	 * @return 受影响的行数
	 */
	Integer insert(Article article);

	/**
	 * 根据id删除文章
	 * 
	 * @param articleId
	 *            文章id
	 * @return 受影响的行数
	 */
	Integer delete(@Param("id") Integer articleId);

	/**
	 * 更新文章
	 * 
	 * @param article
	 *            文章信息（id,[title,summary,content,categoryIds,tagIds,status,
	 *            writeTime,pubTime](可为空，为空表示该字段不修改)）
	 * @return 受影响的行数
	 */
	Integer update(Article article);

	Integer updateStatus(Article article);

	/**
	 * 通过id查找
	 * 
	 * @param articleId
	 *            文章id
	 * @return Article
	 */
	ArticleVo selectVoById(@Param("id") Integer articleId);

    ArticleVo selectVoByCode(@Param("code") String code,
                             @Param("status") Integer articleStatus);
	Article selectById(@Param("id") Integer articleId);

	/**
	 * 通过标题、类别id、标签id进行模糊查找
	 * 注意：categoryId和tagId的值用"id,"表示，比如"1,"，因为规定数据库中存储的格式为id1,id2,id3方式存储
	 * 
	 * @param article
	 *            文章信息（此处可选包括：title、categoryIds、tagIds、status）
	 * @param pageConfig
	 *            分页信息（此处可选包括：pageIndex、pageSize）
	 * @return
	 */
	List<Article> selectBy(@Param("article") Article article,
			@Param("pageConfig") PageConfig pageConfig);

	/**
	 * 通过标题、类别id、标签id进行模糊查找
	 * 注意：categoryId和tagId的值用"id,"表示，比如"1,"，因为规定数据库中存储的格式为id1,id2,id3/方式存储
	 * 
	 * @param article
	 *            文章信息（此处可选包括：title、categoryIds、tagIds、status）
	 * @param pageConfig
	 *            分页信息（此处可选包括：pageIndex、pageSize）
	 * @return
	 */
	List<ArticleVo> selectVoBy(@Param("article") Article article,
			@Param("pageConfig") PageConfig pageConfig);

	/**
	 * 通过标题、类别id、标签id进行模糊查找取得所有记录数量。 注意：参照"selectBy"方法的注意事项，用于配合"selectBy"方法使用
	 * 
	 * @param article
	 *            文章信息（此处可选包括：title、categoryIds、tagIds）
	 * @return
	 */
	Integer selectCountBy(@Param("article") Article article);

	/**
	 * 通过标题精确取得文章
	 * 
	 * @param articleTitle
	 * @return
	 */
	ArticleVo selectVoByTitle(@Param("title") String articleTitle,
			@Param("status") Integer articleStatus);

	Article selectNextOrPreVoBy(@Param("article") Article article,
			@Param("nextOrPreVo") Boolean nextOrPreVo);

	Integer updateCategoryId(@Param("oldCategoryId") Integer oldCategoryId,
			@Param("newCategoryId") Integer newCategoryId);

	/**
	 * 获取所有归档，即获取每年或者每月的归档
	 *
	 * @param articleStatus
	 *            文章状态，传值设定为blog状态 . CommonConstant类中有对应的常量
	 * @param archiveTypeYear
	 *            归档类型：%Y年 . CommonConstant类中有对应的常量
	 * @param archiveTypeYearMonth 归档类型：%Y年%m月 . CommonConstant类中有对应的常量
	 * @return
	 */
	List<ArchiveVo> selectAllArchives(@Param("status") Integer articleStatus,
			@Param("archive_type_year") String archiveTypeYear,
			@Param("archive_type_year_month") String archiveTypeYearMonth);

	/**
	 * 获取某归档对应的所有文章
	 *
	 * @param articleStatus
	 *            文章状态，传值设定为blog状态 . CommonConstant类中有对应的常量
	 * @param archiveType
	 *            归档类型：%Y年 %Y年%m月 . CommonConstant类中有对应的常量
	 * @param monthName
	 *            归档中的具体信息，如：2016年10月
	 * @return
	 */
	List<Article> selectArchiveArticles(@Param("status") Integer articleStatus,
			@Param("archive_type") String archiveType,
			@Param("month_name") String monthName);

    int updateHits(Article article);
}
