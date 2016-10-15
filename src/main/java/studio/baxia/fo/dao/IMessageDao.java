package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.common.PageConfig;
import studio.baxia.fo.pojo.Message;

import java.util.List;

/**
 * Created by Pan on 2016/10/15.
 */
@Repository("iMessageDao")
public interface IMessageDao {

    /**
     * 插入评论信息
     *
     * @param message 评论信息（articleId，parentId，content，userType，authorId）
     * @return 受影响的行数
     */
    public Integer insert(Message message);

    public Integer delete(@Param("ids") List<Integer> messageIds);

    public Integer delete(@Param("articleId") Integer messageArticleId);

    public Integer update(Message message);

    public Message selectById(@Param("id") Integer messageId);

    /**
     * 根据文章id和评论的父id获取分页的评论记录
     *
     * @param messageArticleId 评论的文章id
     * @param messageParentId  评论的父评论id（null:查出该文章的所有评论，为0+:查出对应父评论id的所有评论）
     * @param messageBlockId 评论所在的文章中的评论区的位置，属于第几块（null：表示查出所有）
     * @param pageConfig       分页配置
     * @return List<Message>
     */
    public List<Message> selectByArticleId(@Param("articleId") Integer messageArticleId, @Param("parentId") Integer messageParentId,@Param("blockId")Integer messageBlockId, @Param("pageConfig") PageConfig pageConfig,@Param("reverseOrder")Integer reverseOrder);
}
