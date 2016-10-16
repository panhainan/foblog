package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 文章标签实体信息.
 */
public class Tag {
    private Integer id;
    private String name; //标签名称
    private Integer authorId;//作者id

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
}
