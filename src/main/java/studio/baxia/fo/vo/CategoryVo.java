package studio.baxia.fo.vo;

import studio.baxia.fo.pojo.Category;

public class CategoryVo extends Category {
	private Integer counts;
    private int hits;
    private int countMessages;

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
}
