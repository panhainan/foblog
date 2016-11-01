package studio.baxia.fo.pojo;

import studio.baxia.fo.common.CommonConstant;
import studio.baxia.fo.common.TreeInfo;

/**
 * Created by FirePan on 2016/10/11.
 * 文章类别实体信息.
 * 注意：限制目录级别为两层
 */
public class Category extends TreeInfo {
    private Integer id;
    private Integer parentId = CommonConstant.CATEGORY_DEFAULT_PARENT_ID;//父类别id
    private String name;//名称

    @Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId + ", name="
				+ name + "]";
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
