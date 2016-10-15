package studio.baxia.fo.common;

/**
 * Created by Pan on 2016/10/15.
 * 实现类向TreeInfoResult类转换需要继承此类
 */
public class TreeInfo {
    private Integer id;
    private Integer parentId;

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
}
