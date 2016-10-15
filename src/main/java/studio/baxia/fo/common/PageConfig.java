package studio.baxia.fo.common;

/**
 * Created by Pan on 2016/10/13.
 */
public class PageConfig {
    private Integer pageIndex = 0;//当前记录位置：用于数据库查询的起始位置
    private Integer pageNumber = 1;//当前页数：用于页面传递到后台的前往页码，同时用于显示在页面上
    private Integer pageSize = 10;//每页显示记录数：用于页面传递到后台的每页显示记录数，同时用于显示在页面上
    private Integer allCount;//总的记录数：从服务器的数据库获取总的记录数，返回计算总页数和显示在页面
    private Integer allPage;//总页数：通过总记录数和每页显示记录数计算得出，用于显示在页面上

    private PageConfig() {
    }

    /**
     * 唯一公有构造器
     *
     * @param pageNumber 当前页码:默认值为1
     * @param pageSize   每页显示记录数:默认值为10
     */
    public PageConfig(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageIndex = (this.pageNumber - 1) * this.pageSize;
    }

    /**
     * 通过总记录数初始化所有成员变量（因为构造器的存在，只需要将allCount,allPage赋值即可）
     * @param allCount
     */
    public void initAll(int allCount) {
        this.allCount = allCount;
        this.allPage = this.allCount % this.pageSize == 0 ? this.allCount / this.pageSize : this.allCount / this.pageSize + 1;
    }

    @Override
    public String toString() {
        return "PageConfig{" +
                "pageIndex=" + pageIndex +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", allCount=" + allCount +
                ", allPage=" + allPage +
                '}';
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

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getAllPage() {
        return allPage;
    }

    public void setAllPage(Integer allPage) {
        this.allPage = allPage;
    }
}
