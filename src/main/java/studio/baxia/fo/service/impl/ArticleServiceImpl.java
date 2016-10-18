package studio.baxia.fo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import studio.baxia.fo.common.*;
import studio.baxia.fo.dao.IArticleDao;
import studio.baxia.fo.dao.ICategoryDao;
import studio.baxia.fo.dao.IMessageDao;
import studio.baxia.fo.dao.ITagDao;
import studio.baxia.fo.pojo.Article;
import studio.baxia.fo.pojo.Category;
import studio.baxia.fo.pojo.Message;
import studio.baxia.fo.pojo.Tag;
import studio.baxia.fo.service.IArticleService;

import java.util.Date;
import java.util.List;

/**
 * Created by Pan on 2016/10/16.
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ICategoryDao iCategoryDao;
    @Autowired
    private ITagDao iTagDao;
    @Autowired
    private IArticleDao iArticleDao;
    @Autowired
    private IMessageDao iMessageDao;

    /**
     * 返回结果
     * @param result 受影响的行数（=1+：返回true，=0-：返回false）
     * @return
     */
    private Boolean returnResult(Integer result){
        if(result>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 添加类别
     *
     * @param category 类别（parent_id,name,author_id）
     * @return
     */
    @Override
    public Boolean categoryAdd(Category category) {
        Integer result = iCategoryDao.insert(category);
        return returnResult(result);
    }

    /**
     * 修改类别
     *
     * @param category 类别（id,author_id,parent_id,name）
     * @return
     */
    @Override
    public Boolean categoryEdit(Category category) {
        Integer result = iCategoryDao.update(category);
        return returnResult(result);
    }

    /**
     * 通过类别id删除类别
     *
     * @param categoryId 类别id
     * @param categoryAuthorId 类别作者id
     * @return
     */
    @Override
    public Boolean categoryDeleteById(int categoryId,int categoryAuthorId) {
        Category c = iCategoryDao.selectById(categoryId);
        Integer result = null;
        if(c!=null && c.getAuthorId()==categoryAuthorId){
            if(c.getParentId()==0){
                //根级别目录
                result = iCategoryDao.deleteBy(1, categoryId);
            }
            result += iCategoryDao.deleteById(categoryId);
            return returnResult(result);
        }else{
            //categoryId不存在或者没有权限
            return false;
        }
    }

    /**
     * 通过作者id获取所有类别
     *
     * @param authorId 作者id
     * @return
     */
    @Override
    public List<Category> categoryGetAllBy(int authorId) {
        List<Category> result = iCategoryDao.selectBy(authorId,null);
        return result;
    }

    /**
     * 通过类别id获取类别
     *
     * @param categoryId 类别id
     * @return
     */
    @Override
    public Category categoryGetById(int categoryId) {
        Category result = iCategoryDao.selectById(categoryId);
        return result;
    }

    /**
     * 添加标签
     *
     * @param tag 标签（name,authorId）
     * @return
     */
    @Override
    public Boolean tagAdd(Tag tag) {
        Integer result =iTagDao.insert(tag);
        return returnResult(result);
    }

    /**
     * 通过标签id删除标签
     *
     * @param tagId 标签id
     * @param tagAuthorId 标签作者id
     * @return
     */
    @Override
    public Boolean tagDeleteById(int tagId,int tagAuthorId) {
        Integer result =iTagDao.delete(tagId,tagAuthorId);
        return returnResult(result);
    }

    /**
     * 通过标签id获取标签
     *
     * @param tagId 标签id
     * @param tagAuthorId 标签作者id
     * @return
     */
    @Override
    public Tag tagGetById(int tagId,int tagAuthorId) {
        Tag result =iTagDao.selectById(tagId,tagAuthorId);
        return result;
    }

    /**
     * 通过作者id获取所有标签
     *
     * @param authorId 作者id
     * @return
     */
    @Override
    public List<Tag> tagGetAllBy(int authorId) {
        List<Tag> result = iTagDao.selectBy(authorId);
        return result;
    }

    /**
     * 添加文章
     *
     * @param article 文章信息（title,summary,content,categoryIds,tagIds,authorId,status）
     * @return
     */
    @Override
    public Boolean articleAdd(Article article) {
        if(article.getStatus() == Constant.ACTICLE_STATUS_BLOG){
            article.setPubTime(new Date());
        }
        article.setWriteTime(new Date());
        Integer result = iArticleDao.insert(article);
        return returnResult(result);
    }

    /**
     * 编辑文章
     *
     * @param article 文章信息（id,[title,summary,content,categoryIds,tagIds,status,pubTime](可为空，为空表示该字段不修改)）
     * @return
     */
    @Override
    public Boolean articleEdit(Article article) {
        if(article.getStatus() == Constant.ACTICLE_STATUS_BLOG){
            article.setPubTime(new Date());
        }
        Integer result = iArticleDao.update(article);
        return returnResult(result);
    }

    /**
     * 通过文章id删除文章
     *
     * @param articleId 文章id
     * @param articleAuthorId 文章作者id
     * @return
     */
    @Override
    public Boolean articleDeleteById(int articleId,int articleAuthorId) {
        Article article = iArticleDao.selectById(articleId,articleAuthorId);
        if(article!=null){
            //获取该文章id对应的所有评论记录总数
            Integer counts = iMessageDao.selectCountByArticleId(articleId);
            //返回删除所有评论的文章为id的受影响行数
            Integer results = iMessageDao.deleteByArticleId(articleId);
            if(results == counts){
                Integer result = iArticleDao.delete(articleId,articleAuthorId);
                return returnResult(result);
            }
        }
        return false;
    }

    /**
     * 通过文章id获取文章
     *
     * @param articleId 文章id
     * @param articleAuthorId 文章作者id
     * @return
     */
    @Override
    public Article articleGetById(int articleId,int articleAuthorId) {
        Article result = iArticleDao.selectById(articleId,articleAuthorId);
        return result;
    }

    /**
     * 通过标题取得文章
     *
     * @param articleTitle    文章标题
     * @param articleAuthorId 文章作者id
     * @return
     */
    @Override
    public Article articleGetByTitle(String articleTitle, Integer articleAuthorId) {
        Article article = iArticleDao.selectByTitle(articleTitle, articleAuthorId);
        return article;
    }

    /**
     * 通过作者id、文章状态、分页信息获取分页后的文章列表信息
     *
     * @param articleAuthorId 作者id
     * @param articleStatus   文章状态
     * @param pageConfig      分页信息
     * @return
     */
    @Override
    public PageInfoResult<Article> articleGetAllBy(int articleAuthorId, int articleStatus, PageConfig pageConfig) {
        Article article = new Article();
        article.setAuthorId(articleAuthorId);
        article.setStatus(articleStatus);
        List<Article> result = iArticleDao.selectBy(article, pageConfig);
        Integer resultCount = iArticleDao.selectCountBy(article);
        PageInfoResult<Article> pageInfoResult = new PageInfoResult(result, pageConfig, resultCount);
        return pageInfoResult;
    }

    /**
     * 添加评论
     *
     * @param message 评论信息（articleId，parentId，content，userType，authorId）
     * @return
     */
    @Override
    public Boolean messageAdd(Message message) {
        message.setPubTime(new Date());
        Integer result = iMessageDao.insert(message);
        return returnResult(result);
    }

    /**
     * 通过评论id删除评论
     *
     * @param messageId 评论id
     * @param authorId 操作的作者id
     * @return
     */
    @Override
    public Boolean messageDeleteById(int messageId,int authorId) {
        Message message = iMessageDao.selectById(messageId);
        Integer result = 0;
        if(message!=null){
            Article article = iArticleDao.selectById(message.getArticleId(),authorId);
            if(article!=null){
                if(message.getParentId()!=Constant.MESSAGE_DEFAULT_PARENT_ID){
                    Integer counts = iMessageDao.selectCountBy(messageId,message.getBlockId());
                    result = iMessageDao.deleteBy(messageId,message.getBlockId());
                    if(result==counts){
                        return true;
                    }else{
                        return false;
                    }
                }else{
                    result = iMessageDao.deleteById(messageId);
                    return returnResult(result);
                }
            }
        }
        return false;
    }

    /**
     * 通过文章id删除相关评论
     *
     * @param messageArticleId 文章id
     * @return
     */
    @Override
    public Boolean messageDeleteBy(int messageArticleId) {
        Integer result = iMessageDao.deleteByArticleId(messageArticleId);
        return returnResult(result);
    }

    /**
     * 通过文章id和排序状态获取转换树后的信息
     *
     * @param messageArticleId 文章id
     * @param reverseOrder     排序状态
     * @return
     */
    @Override
    public TreeInfoResult messageGetAllBy(int messageArticleId, int reverseOrder) {
        List<Message> list = iMessageDao.selectByArticleId(messageArticleId,Constant.MESSAGE_NULL_PARENT_ID, null, reverseOrder);
        TreeInfoResult treeInfo = TreeInfoUtil.convertToTreeInfoResult(list, null);
        return treeInfo;
    }
}
