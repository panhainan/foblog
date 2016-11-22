package studio.baxia.fo.vo;

import studio.baxia.fo.pojo.Category;

public class CategoryVo extends Category {
	private Integer counts;

    public CategoryVo categor2Vo(Category category){
        if(category!=null){
            this.setId(category.getId());
            this.setName(category.getName());
            this.setDescription(category.getDescription());
        }
        return this;
    }

    public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	
	
}
