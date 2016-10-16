package studio.baxia.fo.pojo;

import studio.baxia.fo.common.Constant;
import studio.baxia.fo.common.TreeInfo;

import java.util.Date;

/**
 * Created by FirePan on 2016/10/11.
 * 文章留言实体信息.
 * 注意：评论这里采用廖雪峰老师的博客的方式，分块，最多两层。
 */
public class Message extends TreeInfo {
    private Integer id;
    private Integer articleId;//评论的文章id
    private Integer blockId;//所在评论的文章的评论区的第几块区域id
    private Integer parentId = Constant.MESSAGE_DEFAULT_PARENT_ID;//父id
    private String content;//内容
    private Integer userType;//留言作者类别（author作者，guest访客）
    private Integer authorId;//作者id
    private Date pubTime;//评论时间

    public Message() {

    }

    public Message(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", blockId=" + blockId +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                ", userType=" + userType +
                ", authorId=" + authorId +
                ", pubTime=" + pubTime +
                '}';
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}
