package studio.baxia.fo.service;

import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.common.TreeInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Message;
import studio.baxia.fo.pojo.Tag;

import java.util.List;

/**
 * Created by Pan on 2016/10/16.
 */
public interface IArticleService {
    /**
     * 添加类别
     * @param category 类别（parent_id,name,author_id）
     * @return
     */
    Boolean categoryAdd(Category category);

    /**
     * 修改类别
     * @param category 类别（parent_id,name）
     * @return
     */
    Boolean categoryEdit(Category category);

    /**
     * 通过类别id删除类别
     * @param categoryId 类别id
     * @param categoryAuthorId 类别作者id
     * @return
     */
    Boolean categoryDeleteById(int categoryId,int categoryAuthorId);

    /**
     * 通过作者id获取所有类别
     * @param authorId 作者id
     * @return
     */
    List<Category> categoryGetAllBy(int authorId);

    /**
     * 通过类别id获取类别
     * @param categoryId 类别id
     * @return
     */
    Category categoryGetById(int categoryId);

    /**
     * 添加标签
     * @param tag 标签（name,authorId）
     * @return
     */
    Boolean tagAdd(Tag tag);

    /**
     * 通过标签id删除标签
     * @param tagId 标签id
     * @param tagAuthorId 标签作者id
     * @return
     */
    Boolean tagDeleteById(int tagId,int tagAuthorId);

    /**
     * 通过标签id获取标签
     * @param tagId 标签id
     * @param tagAuthorId 标签作者id
     * @return
     */
    Tag tagGetById(int tagId,int tagAuthorId);

    /**
     * 通过作者id获取所有标签
     * @param authorId 作者id
     * @return
     */
    List<Tag> tagGetAllBy(int authorId);

    /**
     * 添加文章
     * @param article 文章信息（title,summary,content,categoryIds,tagIds,authorId,status）
     * @return
     */
    Boolean articleAdd(Article article);

    /**
     * 编辑文章
     * @param article 文章信息（id,authorId,[title,summary,content,categoryIds,tagIds,status,writeTime,pubTime](可为空，为空表示该字段不修改)）
     * @return
     */
    Boolean articleEdit(Article article);

    /**
     * 通过文章id删除文章
     * @param articleId 文章id
     * @param articleAuthorId 文章作者id
     * @return
     */
    Boolean articleDeleteById(int articleId,int articleAuthorId);

    /**
     * 通过文章id获取文章
     * @param articleId 文章id
     * @return
     */
    Article articleGetById(int articleId,int articleAuthorId);

    /**
     * 通过标题取得文章
     * @param articleTitle 文章标题
     * @param articleAuthorId 文章作者id
     * @return
     */
    Article articleGetByTitle(String articleTitle, Integer articleAuthorId);
    /**
     * 通过作者id、文章状态、分页信息获取分页后的文章列表信息
     * @param articleAuthorId 作者id
     * @param articleStatus 文章状态
     * @param pageConfig 分页信息
     * @return
     */
    PageInfoResult<Article> articleGetAllBy(int articleAuthorId,int articleStatus,PageConfig pageConfig);

    /**
     * 添加评论
     * @param message 评论信息（articleId，parentId，content，userType，authorId）
     * @return
     */
    Boolean messageAdd(Message message);

    /**
     * 通过评论id删除评论
     * @param messageId 评论id
     * @param authorId 操作的作者id
     * @return
     */
    Boolean messageDeleteById(int messageId,int authorId);

    /**
     * 通过文章id删除相关评论
     * @param messageArticleId 文章id
     * @return
     */
    Boolean messageDeleteBy(int messageArticleId);

    /**
     * 通过文章id和排序状态获取转换树后的信息
     * @param messageArticleId 文章id
     * @param reverseOrder 排序状态
     * @return
     */
    TreeInfoResult messageGetAllBy(int messageArticleId,int reverseOrder);


}
