package studio.baxia.fo.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan on 2016/10/15.
 * TreeInfo工具类
 */
public class TreeInfoUtil {

    /**
     * 将List<? extends TreeInfo>转换成TreeInfoResult
     * @param treeInfos 实现了TreeInfo的类的集合形式
     * @return TreeInfoResult
     */
    public static TreeInfoResult convertToTreeInfoResult(List<? extends TreeInfo> treeInfos,TreeInfo rootTreeInfo) {
        TreeInfoResult treeRootInfoResult = new TreeInfoResult(rootTreeInfo);
        List<TreeInfoResult> treeRootInfoResultChildrens = new ArrayList<TreeInfoResult>();
        List<TreeInfo> restTreeInfos = new ArrayList<TreeInfo>();
        if(rootTreeInfo==null){
            for (int i = 0; i < treeInfos.size(); i++) {
                TreeInfo treeInfo = treeInfos.get(i);
                if (treeInfo.getParentId() == Constant.MESSAGE_DEFAULT_PARENT_ID) {
                    treeRootInfoResultChildrens.add(new TreeInfoResult(treeInfo));
                } else {
                    restTreeInfos.add(treeInfo);
                }
            }
        }else{
            for (int i = 1; i < treeInfos.size(); i++) {
                TreeInfo treeInfo = treeInfos.get(i);
                if (treeInfo.getParentId() == rootTreeInfo.getId()) {
                    treeRootInfoResultChildrens.add(new TreeInfoResult(treeInfo));
                } else {
                    restTreeInfos.add(treeInfo);
                }
            }
        }
        getChildrenTree(treeRootInfoResultChildrens, restTreeInfos);
        treeRootInfoResult.setChildrens(treeRootInfoResultChildrens);
        return treeRootInfoResult;
    }

    private static void getChildrenTree(List<TreeInfoResult> treeInfoResultChildrens, List<TreeInfo> treeInfos) {
        List<TreeInfo> restTreeInfos;
        for (int i = 0; i < treeInfoResultChildrens.size(); i++) {
            TreeInfoResult treeInfoResult = treeInfoResultChildrens.get(i);
            if (treeInfoResult != null) {
                int parentId = treeInfoResult.getData().getId();
                List<TreeInfoResult> treeInfoChildrens = new ArrayList<TreeInfoResult>();
                restTreeInfos = new ArrayList<>();
                for (int j = 0; j < treeInfos.size(); j++) {
                    TreeInfo treeInfo = treeInfos.get(j);
                    if (treeInfo.getParentId() == parentId) {
                        treeInfoChildrens.add(new TreeInfoResult(treeInfo));
                    } else {
                        restTreeInfos.add(treeInfo);
                    }
                }
                if (treeInfoChildrens.size() != 0) {
                    getChildrenTree(treeInfoChildrens, restTreeInfos);
                }
                treeInfoResult.setChildrens(treeInfoChildrens);
            }
        }
    }

    public static void traverseTreeChildGetIds(TreeInfoResult treeNode, List<Integer> ids) {
        if (treeNode != null) {
            List<TreeInfoResult> list = treeNode.getChildrens();
            if(list!=null){
                for (int i = 0; i < list.size(); i++) {
                    TreeInfoResult node = list.get(i);
                    traverseTreeChildGetIds(node, ids);
                }
                if(treeNode.getData()!=null){
                    ids.add(treeNode.getData().getId());
                }
            }
        }
    }
}