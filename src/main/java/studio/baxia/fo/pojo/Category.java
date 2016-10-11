package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11.
 * 文章类别实体信息.
 */
public class Category {
    private Integer id;
    private String parentId;//父类别id
    private String name;//名称

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", parentId='" + parentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
