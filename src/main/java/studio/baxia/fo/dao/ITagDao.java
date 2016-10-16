package studio.baxia.fo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import studio.baxia.fo.pojo.Tag;

import java.util.List;

/**
 * Created by Pan on 2016/10/16.
 */
@Repository("iTagDao")
public interface ITagDao {
    /**
     * 插入标签
     * @param tag 标签（name,authorId）
     * @return 受影响的行
     */
    Integer insert(Tag tag);

    /**
     * 删除标签
     * @param tagId 标签id
     * @return 受影响的行
     */
    Integer delete(@Param("id")Integer tagId,@Param("authorId")Integer tagAuthorId);

    /**
     * 查找作者的所有标签
     * @param tagAuthorId 作者id
     * @return List<Tag>
     */
    List<Tag> selectBy(@Param("authorId")Integer tagAuthorId);

    /**
     * 通过id查找标签
     * @param tagId 标签id
     * @param tagAuthorId 标签作者id
     * @return Tag
     */
    Tag selectById(@Param("id")Integer tagId,@Param("authorId")Integer tagAuthorId);
}
