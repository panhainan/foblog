package studio.baxia.fo.common;

/**
 * Created by Pan on 2016/10/13.
 */
public class PageConfig {
    private Integer pageIndex;
    private Integer pageSize;
    private Integer allCount;

    public PageConfig() {
    }

    public PageConfig(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }
}
