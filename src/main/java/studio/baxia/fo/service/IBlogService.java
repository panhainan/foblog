package studio.baxia.fo.service;

import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.common.TreeInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Message;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.vo.ArchiveVo;
import studio.baxia.fo.vo.ArticleVo;
import studio.baxia.fo.vo.CategoryVo;
import studio.baxia.fo.vo.TagVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/10/16.
 */
public interface IBlogService {
	/**
	 * 添加类别
	 * 
	 * @param category
	 *            类别（parent_id,name,author_id）
	 * @return
	 */
	Boolean categoryAdd(Category category);

	/**
	 * 修改类别
	 * 
	 * @param category
	 *            类别（parent_id,name）
	 * @return
	 */
	Boolean categoryEdit(Category category);

	/**
	 * 通过类别id删除类别
	 * 
	 * @param categoryId
	 *            类别id
	 * @return
	 */
	Boolean categoryDeleteById(int categoryId);

	/**
	 * 通过作者id获取所有类别
	 * 
	 * @return
	 */
	List<Category> categoryGetAllBy();

	/**
	 * 通过类别id获取类别
	 * 
	 * @param categoryId
	 *            类别id
	 * @return
	 */
	Category categoryGetById(int categoryId);

	/**
	 * 添加标签
	 * 
	 * @param tag
	 *            标签（name）
	 * @return
	 */
	Boolean tagAdd(Tag tag);

	Boolean tagEdit(Tag tag);

	/**
	 * 通过标签id删除标签
	 * 
	 * @param tagId
	 *            标签id
	 * @return
	 */
	Boolean tagDeleteById(int tagId);

	/**
	 * 通过标签id获取标签
	 * 
	 * @param tagId
	 *            标签id
	 * @return
	 */
	Tag tagGetById(int tagId);

	/**
	 * 通过作者id获取所有标签
	 * 
	 * @return
	 */
	List<Tag> tagGetAllBy();

	List<TagVo> tagGetAllVoBy(Integer articleStatus);

	/**
	 * 添加文章
	 * 
	 * @param article
	 *            文章信息（title,summary,content,categoryIds,tagIds,status）
	 * @return
	 */
	Integer articleAdd(ArticleVo article);

	/**
	 * 编辑文章
	 * 
	 * @param article
	 *            文章信息（id,[title,summary,content,categoryIds,tagIds,status,
	 *            writeTime,pubTime](可为空，为空表示该字段不修改)）
	 * @return
	 */
	Integer articleEdit(ArticleVo article);

	/**
	 * 通过文章id删除文章
	 * 
	 * @param articleId
	 *            文章id
	 * @return
	 */
	Boolean articleDeleteById(int articleId);

	/**
	 * 通过文章id获取文章
	 * 
	 * @param articleId
	 *            文章id
	 * @return
	 */
	ArticleVo articleVoGetById(int articleId);

	/**
	 * 通过标题取得文章
	 * 
	 * @param articleTitle
	 *            文章标题
	 * @return
	 */
	Map<String, Object> articleVoGetByTitle(String articleTitle);

	/**
	 * 通过文章状态、分页信息获取分页后的文章列表信息
	 * 
	 * @param articleStatus
	 *            文章状态
	 * @param pageConfig
	 *            分页信息
	 * @return
	 */
	PageInfoResult<Article> articleGetAllBy(Integer articleStatus,
			PageConfig pageConfig);

	/**
	 * 添加评论
	 * 
	 * @param message
	 *            评论信息（articleId，parentId，content，userType，authorId）
	 * @return
	 */
	Boolean messageAdd(Message message);

	/**
	 * 通过评论id删除评论
	 * 
	 * @param messageId
	 *            评论id
	 * @param authorId
	 *            操作的作者id
	 * @return
	 */
	Boolean messageDeleteById(int messageId, int authorId);

	/**
	 * 通过文章id删除相关评论
	 * 
	 * @param messageArticleId
	 *            文章id
	 * @return
	 */
	Boolean messageDeleteBy(int messageArticleId);

	/**
	 * 通过文章id和排序状态获取转换树后的信息
	 * 
	 * @param messageArticleId
	 *            文章id
	 * @param reverseOrder
	 *            排序状态
	 * @return
	 */
	TreeInfoResult messageGetAllBy(int messageArticleId, int reverseOrder);

	PageInfoResult<ArticleVo> articleGetAllManageBy(Integer articleStatus,
			PageConfig pageConfig);

	List<CategoryVo> categoryGetAllVoBy(Integer articleStatus);

	List<ArticleVo> articleGetAllByCategoryName(String categoryName);

	List<ArticleVo> articleGetAllByTagName(String tagName);

	List<Article> articleGetAllByCategoryId(int id, Integer articleStatus);

	Boolean articleDeleteTag(int tagId, int articleId);

	List<Article> articleGetAllByTagId(int tagId, Integer articleStatus);

	/**
	 * 获取所有归档（通过文章状态和归档类型）
	 * @param articleStatus 包含：“博客” 和 “草稿” 两种状态 . CommonConstant类中有对应的常量
	 * @param archiveType 包含：“年月” 和 “年” 两种类型 . CommonConstant类中有对应的常量
	 * @return
	 */
	List<ArchiveVo> archiveGetAllVo(Integer articleStatus,
			String archiveType);

	/**
	 * 获取对应归档项的所有文章（待分页处理）
	 * @param name 归档项名称，如 “2016年10月”
	 * @param articleStatus 包含：“博客” 和 “草稿” 两种状态 . CommonConstant类中有对应的常量
	 * @param archiveType 包含：“年月” 和 “年” 两种类型 . CommonConstant类中有对应的常量
	 * @return
	 */
	List<Article> archiveGetAllArticles(String name, Integer articleStatus,
			String archiveType);

}
