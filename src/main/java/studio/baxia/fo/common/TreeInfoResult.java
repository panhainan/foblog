package studio.baxia.fo.common;

import java.util.List;

/**
 * Created by Pan on 2016/10/15.
 * 将一个继承了TreeInfo的类集合转换的结果
 */
public class TreeInfoResult {
    private List<TreeInfoResult> childrens;
    private TreeInfo data;

    private TreeInfoResult(){

    }

    public TreeInfoResult(TreeInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TreeInfoResult{" +
                "\nchildrens=" + childrens +
                ", \ndata=" + data +
                "\n}";
    }

    public List<TreeInfoResult> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<TreeInfoResult> childrens) {
        this.childrens = childrens;
    }

    public TreeInfo getData() {
        return data;
    }

    public void setData(TreeInfo data) {
        this.data = data;
    }
}
