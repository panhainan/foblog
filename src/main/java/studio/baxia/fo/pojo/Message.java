package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 文章留言实体信息.
 */
public class Message {
    private Integer id;
    private Integer parentId;//父id
    private String content;//内容
    private Integer userType;//留言作者类别（author作者，guest访客）
    private Integer authorId;//作者id

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", content='" + content + '\'' +
                ", userType=" + userType +
                ", authorId=" + authorId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
