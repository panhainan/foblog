package studio.baxia.fo.pojo;

import java.util.Date;

/**
 * Created by FirePan on 2016/10/11.
 * 文章实体信息.
 */
public class Article {
    private Integer id;
    private String title;//标题
    private String summary;//概要
    private String content;//内容
    private String categoryIds;//类别id,多个,用/分开
    private String tagIds;//标签id,多个,用/分开
    private Integer authorId;//作者id
    private Integer status;//文章状态：0-草稿，1-博文
    private Date writeTime;//撰写时间
    private Date pubTime; //发布时间

    @Override
    public String toString() {
        return "\nArticle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", categoryIds='" + categoryIds + '\'' +
                ", tagIds='" + tagIds + '\'' +
                ", authorId=" + authorId +
                ", status=" + status +
                ", writeTime=" + writeTime +
                ", pubTime=" + pubTime +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(String categoryIds) {
        this.categoryIds = categoryIds;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Date writeTime) {
        this.writeTime = writeTime;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}
