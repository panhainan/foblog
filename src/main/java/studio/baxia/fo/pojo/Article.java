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
    private Integer categoryIds;//类别id
    private String tagIds;//标签id,多个,用,分开
    private Integer status;//文章状态：0-草稿，1-博文
    private Date writeTime;//撰写时间
    private Date pubTime; //发布时间
    private int hits;//点击数
    private int countMessages;
    private String code;//文章对外编号

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", categoryIds=" + categoryIds +
                ", tagIds='" + tagIds + '\'' +
                ", status=" + status +
                ", writeTime=" + writeTime +
                ", pubTime=" + pubTime +
                ", hits=" + hits +
                ", countMessages=" + countMessages +
                ", code='" + code + '\'' +
                '}';
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



    public Integer getCategoryIds() {
		return categoryIds;
	}

	public Article setCategoryIds(Integer categoryIds) {
		this.categoryIds = categoryIds;
		return this;
	}

	public String getTagIds() {
        return tagIds;
    }

    public Article setTagIds(String tagIds) {
        this.tagIds = tagIds;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Article setStatus(Integer status) {
        this.status = status;
        return this;
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

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getCountMessages() {
        return countMessages;
    }

    public void setCountMessages(int countMessages) {
        this.countMessages = countMessages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
