package studio.baxia.fo.common;

import java.util.List;

/**
 * Created by Pan on 2016/10/15.
 */
public class PageInfoResult<T> {
    private List<T> list;
    private PageConfig pageConfig;

    private PageInfoResult() {
    }

    /**
     * 唯一公有构造器，并完全初始化PageConfig的所有变量
     * @param list List<T>
     * @param pageConfig PageConfig
     * @param infoCounts 总记录数
     */
    public PageInfoResult(List<T> list, PageConfig pageConfig,int infoCounts) {
        this.list = list;
        pageConfig.initAll(infoCounts);
        this.pageConfig = pageConfig;
    }

    @Override
    public String toString() {
        return "PageInfoResult{\n" +
                "list=" + list +
                ", \npageConfig=" + pageConfig +
                "\n}";
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public PageConfig getPageConfig() {
        return pageConfig;
    }

    public void setPageConfig(PageConfig pageConfig) {
        this.pageConfig = pageConfig;
    }
}
