package studio.baxia.fo.pojo;

import org.hibernate.validator.constraints.NotEmpty;
import studio.baxia.fo.common.TreeInfo;

/**
 * Created by FirePan on 2016/10/11.
 * 文章类别实体信息.
 * 注意：限制目录级别为两层
 */
public class Category extends TreeInfo {
    private Integer id;
    @NotEmpty(message = "名称不能为空")
    private String name;//名称
    @NotEmpty(message = "描述不能为空")
    private String description;
    private String code;
    /**
     * 类别状态
     */
    private boolean status;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
