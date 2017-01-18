package studio.baxia.fo.service;

import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.common.PageInfoResult;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.vo.ArchiveVo;
import studio.baxia.fo.vo.ArticleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by Pan on 2016/12/4.
 */
public interface IBArticleService {
    /**
     * 添加文章
     *
     * @param article 文章信息（title,summary,content,categoryIds,tagIds,status）
     * @return
     */
    Integer add(ArticleVo article);

    /**
     * 编辑文章
     *
     * @param article 文章信息（id,[title,summary,content,categoryIds,tagIds,status,
     *                writeTime,pubTime](可为空，为空表示该字段不修改)）
     * @return
     */
    Integer edit(ArticleVo article);

    /**
     * 通过文章id删除文章
     *
     * @param articleId 文章id
     * @return
     */
    Boolean deleteById(int articleId);

    public Article getById(int articleId);

    /**
     * 通过文章id获取文章
     *
     * @param articleId 文章id
     * @return
     */
    ArticleVo getVoById(int articleId);
    Map<String, Object> getVoByCode(String code,Integer articleStatus);
    /**
     * 通过标题取得文章
     *
     * @param articleTitle 文章标题
     * @return
     */
    Map<String, Object> getVoByTitle(String articleTitle);

    /**
     * 通过文章状态、分页信息获取分页后的文章列表信息
     *
     * @param articleStatus 文章状态
     * @param pageConfig    分页信息
     * @return
     */
    PageInfoResult<Article> getAllBy(Integer articleStatus,
                                            PageConfig pageConfig);

    PageInfoResult<ArticleVo> getAllManageBy(Integer articleStatus, PageConfig pageConfig);

    Map<String,Object> getAllByCategoryName(String categoryName,boolean status);
    Map<String,Object> getAllByCategoryCode(String code, boolean categoryAll);

    List<ArticleVo> getAllByTagName(String tagName);

    List<Article> getAllByCategoryId(int id, Integer articleStatus);

    Boolean deleteTag(int tagId, int articleId);

    List<Article> getAllByTagId(int tagId, Integer articleStatus);

    /**
     * 获取所有归档（通过文章状态和归档类型）
     * @param articleStatus 包含：“博客” 和 “草稿” 两种状态 . CommonConstant类中有对应的常量
     * @param archiveTypeYear CommonConstant类中有对应的常量“年”
     * @param archiveTypeYearMonth CommonConstant类中有对应的常量“年月”
     * @return
     */
    List<ArchiveVo> getAllArchiveVo(Integer articleStatus,
                                    String archiveTypeYear, String archiveTypeYearMonth);

    /**
     * 获取对应归档项的所有文章（待分页处理）
     * @param name 归档项名称，如 “2016年10月”
     * @param articleStatus 包含：“博客” 和 “草稿” 两种状态 . CommonConstant类中有对应的常量
     * @param archiveType 包含：“年月” 和 “年” 两种类型 . CommonConstant类中有对应的常量
     * @return
     */
    List<Article> getAllArchiveArticles(String name, Integer articleStatus,
                                        String archiveType);

}
