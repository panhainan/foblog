package studio.baxia.fo.common;

import java.util.List;

/**
 * Created by Pan on 2016/10/15.
 */
public class PageInfoResult<T> {
    private List<T> list;
    private PageConfig pageConfig;

    public PageInfoResult() {
    }

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
