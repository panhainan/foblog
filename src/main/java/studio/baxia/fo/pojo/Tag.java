package studio.baxia.fo.pojo;

/**
 * Created by FirePan on 2016/10/11. 文章标签实体信息.
 */
public class Tag {
	private Integer id;
	private String name; // 标签名称

	public Tag() {
		super();
	}

	public Tag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", name=" + name + "]";
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

}
